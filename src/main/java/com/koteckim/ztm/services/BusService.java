package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Bus;
import com.koteckim.ztm.model.BusStop;

import java.util.List;

public interface BusService {

    public List<Bus> getAllBuses();

    public Bus getBusById(int id);

    public void addBus(Bus bus);

    public void deleteBus(int id);

}
