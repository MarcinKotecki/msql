package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Route;
import com.koteckim.ztm.repository.BusStopOnRouteRepository;
import com.koteckim.ztm.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RouteServiceImplementation implements RouteService {

    @Autowired
    RouteRepository RouteRepository;

    @Autowired
    BusStopOnRouteRepository BusStopOnRouteRepository;

    @Override
    public List<Route> getAllRoutes() {
        return (List<Route>) RouteRepository.findAll();
    }

    @Override
    public List<Route> getAllRoutesWithStops() {
        List<Route> routes = (List<Route>) RouteRepository.findAll();
        routes.forEach(route -> route.setBusStops(BusStopOnRouteRepository.findByRouteId(route.getId())));
        return routes;
    }

    @Override
    public Route getRouteById(int id) {
        return RouteRepository.findById(id).get();
    }

    @Override
    public Route getRouteWithStopsById(int id) {
        Route route = RouteRepository.findById(id).get();
        route.setBusStops(BusStopOnRouteRepository.findByRouteId(route.getId()));
        return route;
    }

    @Override
    public void addRoute(Route Route) {
        RouteRepository.save(Route);
    }

    @Override
    public void deleteRoute(int id) {
        RouteRepository.deleteById(id);
    }
}
