package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStopOnCourse;
import com.koteckim.ztm.repository.BusStopOnCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusStopOnCourseServiceImplementation implements BusStopOnCourseService {

    @Autowired
    BusStopOnCourseRepository BusStopOnCourseRepository;

    @Override
    public List<BusStopOnCourse> getAllBusStopOnCourses() {
        return (List<BusStopOnCourse>) BusStopOnCourseRepository.findAll();
    }

    @Override
    public BusStopOnCourse getBusStopOnCourseById(int id) {
        return BusStopOnCourseRepository.findById(id).get();
    }

    @Override
    public void addBusStopOnCourse(BusStopOnCourse BusStopOnCourse) {
        BusStopOnCourseRepository.save(BusStopOnCourse);
    }

    @Override
    public void deleteBusStopOnCourse(int id) {
        BusStopOnCourseRepository.deleteById(id);
    }
}
