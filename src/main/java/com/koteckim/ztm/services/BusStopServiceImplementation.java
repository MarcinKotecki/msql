package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStop;
import com.koteckim.ztm.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusStopServiceImplementation implements BusStopService {

    @Autowired
    BusStopRepository BusStopRepository;

    @Override
    public List<BusStop> getAllBusStops() {
        return (List<BusStop>) BusStopRepository.findAll();
    }

    @Override
    public BusStop getBusStopById(int id) {
        return BusStopRepository.findById(id).get();
    }

    @Override
    public void addBusStop(BusStop BusStop) {
        BusStopRepository.save(BusStop);
    }

    @Override
    public void deleteBusStop(int id) {
        BusStopRepository.deleteById(id);
    }
}
