package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStop;

import java.util.List;

public interface BusStopService {

    public List<BusStop> getAllBusStops();

    public BusStop getBusStopById(int id);

    public void addBusStop(BusStop course);

    public void deleteBusStop(int id);

}
