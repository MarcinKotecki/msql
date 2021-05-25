package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getAllCourses();

    public List<Course> getAllCoursesWithStops();

    public Course getCourseById(int id);

    public Course getCourseByIdWithStops(int id);

    public void addCourse(Course course);

    public void deleteCourse(int id);

}
