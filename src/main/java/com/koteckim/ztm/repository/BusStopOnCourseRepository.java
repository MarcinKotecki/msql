package com.koteckim.ztm.repository;

import com.koteckim.ztm.model.BusStopOnCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStopOnCourseRepository extends CrudRepository<BusStopOnCourse, Integer> {

    @Query(
            value = "SELECT * FROM bus_stops_on_course WHERE course_id = :sid",
            nativeQuery=true
    )
    public List<BusStopOnCourse> findByCourseId(@Param("sid") int sid);

}
