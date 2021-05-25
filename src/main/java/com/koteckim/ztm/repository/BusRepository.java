package com.koteckim.ztm.repository;

import com.koteckim.ztm.model.Bus;
import com.koteckim.ztm.model.BusStop;
import org.springframework.data.repository.CrudRepository;

public interface BusRepository extends CrudRepository<Bus, Integer> {

}
