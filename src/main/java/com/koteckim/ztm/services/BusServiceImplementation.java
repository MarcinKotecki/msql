package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Bus;
import com.koteckim.ztm.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusServiceImplementation implements BusService {

    @Autowired
    BusRepository BusRepository;

    @Override
    public List<Bus> getAllBuses() {
        return (List<Bus>) BusRepository.findAll();
    }

    @Override
    public Bus getBusById(int id) {
        return BusRepository.findById(id).get();
    }

    @Override
    public void addBus(Bus Bus) {
        BusRepository.save(Bus);
    }

    @Override
    public void deleteBus(int id) {
        BusRepository.deleteById(id);
    }
}
