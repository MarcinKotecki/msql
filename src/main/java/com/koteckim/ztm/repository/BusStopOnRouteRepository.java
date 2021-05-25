package com.koteckim.ztm.repository;

import com.koteckim.ztm.model.BusStopOnRoute;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStopOnRouteRepository extends CrudRepository<BusStopOnRoute, Integer> {

    @Query(
            value = "SELECT * FROM bus_stops_on_route WHERE route_id = :sid",
            nativeQuery=true
    )
    public List<BusStopOnRoute> findByRouteId(@Param("sid") int sid);

}
