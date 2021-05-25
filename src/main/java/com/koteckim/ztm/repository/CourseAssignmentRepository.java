package com.koteckim.ztm.repository;

import com.koteckim.ztm.model.BusStopOnRoute;
import com.koteckim.ztm.model.CourseAssignment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseAssignmentRepository extends CrudRepository<CourseAssignment, Integer> {

    @Query(
            value = "SELECT * FROM course_assignments WHERE day = :day AND (bus_id = :bus OR driver_id = :driver)",
            nativeQuery = true
    )
    public List<CourseAssignment> findPossibleCollisions(@Param("bus") int bus, @Param("driver") int driver, @Param("day") String day);

}
