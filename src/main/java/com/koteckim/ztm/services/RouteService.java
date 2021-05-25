package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Route;

import java.util.List;

public interface RouteService {

    public List<Route> getAllRoutes();

    public List<Route> getAllRoutesWithStops();

    public Route getRouteById(int id);

    public Route getRouteWithStopsById(int id);

    public void addRoute(Route course);

    public void deleteRoute(int id);

}
