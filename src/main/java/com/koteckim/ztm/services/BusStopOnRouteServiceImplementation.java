package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStopOnRoute;
import com.koteckim.ztm.repository.BusStopOnRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusStopOnRouteServiceImplementation implements BusStopOnRouteService {

    @Autowired
    BusStopOnRouteRepository BusStopOnRouteRepository;

    @Override
    public List<BusStopOnRoute> getAllBusStopOnRoutes() {
        return (List<BusStopOnRoute>) BusStopOnRouteRepository.findAll();
    }

    @Override
    public BusStopOnRoute getBusStopOnRouteById(int id) {
        return BusStopOnRouteRepository.findById(id).get();
    }

    @Override
    public void addBusStopOnRoute(BusStopOnRoute BusStopOnRoute) {
        BusStopOnRouteRepository.save(BusStopOnRoute);
    }

    @Override
    public void deleteBusStopOnRoute(int id) {
        BusStopOnRouteRepository.deleteById(id);
    }
}
