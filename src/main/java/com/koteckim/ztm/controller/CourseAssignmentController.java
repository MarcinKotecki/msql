package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.*;
import com.koteckim.ztm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "")
public class CourseAssignmentController {

    @Autowired
    CourseService courseService;
    @Autowired
    BusService busService;
    @Autowired
    CourseAssignmentService courseAssignmentService;
    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/driver/{id}/course_assignments", method = GET)
    public ModelAndView list(@PathVariable int id, @RequestParam Optional<String> month) {
        ModelAndView model = new ModelAndView("course_assignments");
        List<CourseAssignment> courseAssignments = courseAssignmentService.getAllCourseAssignmentsWithStops().stream().filter(x -> x.getDriver() != null && x.getDriver().getId() == id).collect(Collectors.toList());
        List<String> months = courseAssignments.stream().map(a->a.getDay().substring(0,7)).distinct().collect(Collectors.toList());
        courseAssignments = courseAssignments.stream().filter(a -> a.getDay().contains(month.orElse(""))).collect(Collectors.toList());
        model.addObject("courseAssignments", courseAssignments);
        model.addObject("months", months);
        Driver driver = driverService.getDriverById(id);
        model.addObject("driver", driver);
        return model;
    }

    @RequestMapping(value = "/driver/{id}/course_assignments/add", method = GET)
    public ModelAndView addAssignment(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("action", "Przypisz");
        List<Course> courses = courseService.getAllCourses();
        List<Bus> buses = busService.getAllBuses();
        model.addObject("courses", courses);
        model.addObject("buses", buses);
        model.addObject("driverId", id);
        CourseAssignment courseAssignment = new CourseAssignment();
        model.addObject("courseAssignmentForm", courseAssignment);
        model.addObject("collision", Optional.empty());
        model.addObject("assignment", Optional.empty());
        model.setViewName("course_assignment_form");
        return model;
    }

    @RequestMapping(value = "/course_assignment/{id}/edit", method = GET)
    public ModelAndView editAssignment(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("action", "Edytuj");
        CourseAssignment courseAssignment = courseAssignmentService.getCourseAssignmentById(id);
        model.addObject("courseAssignment", courseAssignment);
        model.setViewName("course_assignment_form");
        return model;
    }

    @RequestMapping(value = "/driver/{id}/course_assignments/add", method = POST)
    public ModelAndView add(@ModelAttribute("courseAssignmentForm") CourseAssignment courseAssignment) {
        Optional<CourseAssignment> collision = courseAssignmentService.findCollision(courseAssignment);
        if (collision.isEmpty()) {
            courseAssignmentService.addCourseAssignment(courseAssignment);
            return new ModelAndView("redirect:/driver/" + courseAssignment.getDriver().getId() + "/course_assignments");
        } else {
            ModelAndView model = new ModelAndView();
            model.addObject("action", "Przypisz");
            List<Course> courses = courseService.getAllCourses();
            List<Bus> buses = busService.getAllBuses();
            model.addObject("courses", courses);
            model.addObject("buses", buses);
            model.addObject("driverId", courseAssignment.getDriver().getId());
            CourseAssignment courseAssignmentNew = new CourseAssignment();
            model.addObject("courseAssignmentForm", courseAssignmentNew);
            model.addObject("collision", collision);
            model.addObject("assignment", Optional.of(courseAssignment));
            model.setViewName("course_assignment_form");
            return model;
        }
    }

}
