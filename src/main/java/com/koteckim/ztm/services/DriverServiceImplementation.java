package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Driver;
import com.koteckim.ztm.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImplementation implements DriverService {

    @Autowired
    DriverRepository DriverRepository;

    @Override
    public List<Driver> getAllDrivers() {
        return (List<Driver>) DriverRepository.findAll();
    }

    @Override
    public Driver getDriverById(int id) {
        return DriverRepository.findById(id).get();
    }

    @Override
    public void addDriver(Driver Driver) {
        DriverRepository.save(Driver);
    }

    @Override
    public void deleteDriver(int id) {
        DriverRepository.deleteById(id);
    }
}
