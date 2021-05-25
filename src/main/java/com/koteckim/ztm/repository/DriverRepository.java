package com.koteckim.ztm.repository;

import com.koteckim.ztm.model.BusStop;
import com.koteckim.ztm.model.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Integer> {

}
