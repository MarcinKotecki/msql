package com.koteckim.ztm.services;

import com.koteckim.ztm.model.Driver;

import java.util.List;

public interface DriverService {

    public List<Driver> getAllDrivers();

    public Driver getDriverById(int id);

    public void addDriver(Driver driver);

    public void deleteDriver(int id);

}
