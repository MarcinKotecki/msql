package com.koteckim.ztm.services;

import com.koteckim.ztm.model.CourseAssignment;

import java.util.List;
import java.util.Optional;

public interface CourseAssignmentService {

    public List<CourseAssignment> getAllCourseAssignments();
    public List<CourseAssignment> getAllCourseAssignmentsWithStops();

    public Optional<CourseAssignment> findCollision(CourseAssignment courseAssignment);

    public CourseAssignment getCourseAssignmentById(int id);

    public void addCourseAssignment(CourseAssignment course);

    public void deleteCourseAssignment(int id);

}
