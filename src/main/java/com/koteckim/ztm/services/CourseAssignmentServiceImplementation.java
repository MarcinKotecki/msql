package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStopOnCourse;
import com.koteckim.ztm.model.Course;
import com.koteckim.ztm.model.CourseAssignment;
import com.koteckim.ztm.repository.BusStopOnCourseRepository;
import com.koteckim.ztm.repository.CourseAssignmentRepository;
import com.koteckim.ztm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseAssignmentServiceImplementation implements CourseAssignmentService {

    @Autowired
    CourseAssignmentRepository CourseAssignmentRepository;

    @Autowired
    BusStopOnCourseRepository busStopOnCourseRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseService courseService;

    @Override
    public List<CourseAssignment> getAllCourseAssignments() {
        return (List<CourseAssignment>) CourseAssignmentRepository.findAll();
    }

    @Override
    public List<CourseAssignment> getAllCourseAssignmentsWithStops() {
        List<CourseAssignment> courseAssignments = (List<CourseAssignment>) CourseAssignmentRepository.findAll();
        courseAssignments.forEach(x -> x.getCourse().setBusStops(busStopOnCourseRepository.findByCourseId(x.getCourse().getId())));
        return courseAssignments;
    }

    private int calcTime(String time) {
        List<Integer> v = Arrays.stream(time.split(":")).map(Integer::parseInt).collect(Collectors.toList());
        return v.get(0) * 60 + v.get(1);
    }

    public Optional<CourseAssignment> findCollision(CourseAssignment courseAssignment) {
        List<CourseAssignment> possibleCollisions = CourseAssignmentRepository.findPossibleCollisions(
                courseAssignment.getBus().getId(),
                courseAssignment.getDriver().getId(),
                courseAssignment.getDay()
        );

        Course aCourse = courseService.getCourseByIdWithStops(courseAssignment.getCourse().getId());
        List<BusStopOnCourse> aBusStopsOnCourse = aCourse.getBusStops();
        int aStart = calcTime(aBusStopsOnCourse.get(0).getTime());
        int aEnd = calcTime(aBusStopsOnCourse.get(aBusStopsOnCourse.size() - 1).getTime());
        Optional<CourseAssignment> collidingAssignment = Optional.empty();
        for (CourseAssignment x : possibleCollisions) {
            Course xCourse = courseService.getCourseByIdWithStops(x.getCourse().getId());
            List<BusStopOnCourse> xBusStopsOnCourse = xCourse.getBusStops();
            int xStart = calcTime(xBusStopsOnCourse.get(0).getTime());
            int xEnd = calcTime(xBusStopsOnCourse.get(xBusStopsOnCourse.size() - 1).getTime());
            if (aStart < xEnd && xStart < aEnd) collidingAssignment = Optional.of(x);
        }
        return collidingAssignment;
    }

    @Override
    public CourseAssignment getCourseAssignmentById(int id) {
        return CourseAssignmentRepository.findById(id).get();
    }

    @Override
    public void addCourseAssignment(CourseAssignment CourseAssignment) {
        CourseAssignmentRepository.save(CourseAssignment);
    }

    @Override
    public void deleteCourseAssignment(int id) {
        CourseAssignmentRepository.deleteById(id);
    }
}
