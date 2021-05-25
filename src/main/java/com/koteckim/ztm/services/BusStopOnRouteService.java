package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStopOnRoute;

import java.util.List;

public interface BusStopOnRouteService {

    public List<BusStopOnRoute> getAllBusStopOnRoutes();

    public BusStopOnRoute getBusStopOnRouteById(int id);

    public void addBusStopOnRoute(BusStopOnRoute course);

    public void deleteBusStopOnRoute(int id);

}
