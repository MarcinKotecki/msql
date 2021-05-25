package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStopOnCourse;

import java.util.List;

public interface BusStopOnCourseService {

    public List<BusStopOnCourse> getAllBusStopOnCourses();

    public BusStopOnCourse getBusStopOnCourseById(int id);

    public void addBusStopOnCourse(BusStopOnCourse course);

    public void deleteBusStopOnCourse(int id);

}
