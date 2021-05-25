package com.koteckim.ztm.services;

import com.koteckim.ztm.model.BusStop;
import com.koteckim.ztm.model.Timetable;

import java.util.List;

public interface TimetableService {

    List<Timetable> getTimetablesForBusStop(BusStop busStop);
}
