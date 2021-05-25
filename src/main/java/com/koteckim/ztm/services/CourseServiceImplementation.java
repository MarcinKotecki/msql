package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Course;
import com.koteckim.ztm.repository.BusStopOnCourseRepository;
import com.koteckim.ztm.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImplementation implements CourseService {

    @Autowired
    CourseRepository CourseRepository;

    @Autowired
    BusStopOnCourseRepository BusStopOnCourseRepository;

    @Override
    public List<Course> getAllCourses() {
        return (List<Course>) CourseRepository.findAll();
    }

    @Override
    public List<Course> getAllCoursesWithStops() {
        List<Course> Courses = (List<Course>) CourseRepository.findAll();
        Courses.forEach(Course -> Course.setBusStops(BusStopOnCourseRepository.findByCourseId(Course.getId())));
        return Courses;
    }

    @Override
    public Course getCourseById(int id) {
        return CourseRepository.findById(id).get();
    }

    @Override
    public Course getCourseByIdWithStops(int id) {
        Course course = CourseRepository.findById(id).get();
        course.setBusStops(BusStopOnCourseRepository.findByCourseId(course.getId()));
        return course;
    }

    @Override
    public void addCourse(Course Course) {
        CourseRepository.save(Course);
    }

    @Override
    public void deleteCourse(int id) {
        CourseRepository.deleteById(id);
    }
}
