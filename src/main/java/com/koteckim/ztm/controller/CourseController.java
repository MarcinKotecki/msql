package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.*;
import com.koteckim.ztm.services.BusStopOnCourseService;
import com.koteckim.ztm.services.BusStopOnRouteService;
import com.koteckim.ztm.services.CourseService;
import com.koteckim.ztm.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    RouteService routeService;

    @Autowired
    BusStopOnCourseService busStopOnCourseService;

    @Autowired
    BusStopOnRouteService busStopOnRouteService;

    @RequestMapping(value = "/route/{id}/courses", method = RequestMethod.GET)
    public ModelAndView list(@PathVariable int id) {
        ModelAndView model = new ModelAndView("courses");
        List<Course> Courses = courseService.getAllCoursesWithStops().stream().filter(x -> x.getRoute() != null && x.getRoute().getId() == id).collect(Collectors.toList());
        model.addObject("courses", Courses);
        String routeName = routeService.getRouteById(id).getName();
        model.addObject("routeName", id);
        model.addObject("routeId", id);
        return model;
    }

    @RequestMapping(value = "/route/{id}/courses/add", method = RequestMethod.GET)
    public ModelAndView addRoute(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("action", "Dodaj");
        List<BusStopOnRoute> busStops = routeService.getRouteWithStopsById(id).getBusStops();
        System.out.println("aaa " + busStops.toString());
        model.addObject("busStops", busStops);
        model.addObject("routeId", id);
        model.setViewName("course_form");
        return model;
    }

    @RequestMapping(value = "/course/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editBus(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        model.addObject("action", "Edytuj");
        Course course = courseService.getCourseById(id);
        List<BusStopOnRoute> busStops = routeService.getRouteWithStopsById(course.getRoute().getId()).getBusStops();
        System.out.println("aaa " + busStops.toString());
        model.addObject("busStops", busStops);
        model.addObject("routeId", course.getRoute().getId());
        model.setViewName("course_form");
        return model;
    }

    @RequestMapping(value = "/route/{id}/courses/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

        Course course = new Course();
        Route route = routeService.getRouteWithStopsById(Integer.parseInt(params.get("routeId")[0]));

        course.setRoute(route);
        course.setType(params.get("type")[0]);
        courseService.addCourse(course);
        System.out.println("courseid " + course.getRoute().getId());

        params.forEach((a, b) -> {
            if (a.contains("bs")) {
                BusStopOnCourse busStopOnCourse = new BusStopOnCourse();
                int i = Integer.parseInt(a.substring(2));
                busStopOnCourse.setCourse(course);
                busStopOnCourse.setTime(b[0]);
                busStopOnCourse.setBusStopOnRoute(busStopOnRouteService.getBusStopOnRouteById(i));
                busStopOnCourseService.addBusStopOnCourse(busStopOnCourse);
            }
        });

        return new ModelAndView("redirect:/route/" + Integer.parseInt(params.get("routeId")[0]) + "/courses");
    }

}
