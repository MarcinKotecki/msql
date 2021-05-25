package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.BusStop;
import com.koteckim.ztm.model.Timetable;
import com.koteckim.ztm.services.BusStopService;
import com.koteckim.ztm.services.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "")
public class BusStopController {

    @Autowired
    BusStopService busStopService;

    @Autowired
    TimetableService timetableService;

    @RequestMapping(value = "/bus_stops", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("bus_stops");
        List<BusStop> busStops = busStopService.getAllBusStops();
        model.addObject("busStops", busStops);
        return model;
    }

    @RequestMapping(value = "/bus_stops/add", method = RequestMethod.GET)
    public ModelAndView addBusStop() {
        ModelAndView model = new ModelAndView();
        BusStop busStop = new BusStop();
        model.addObject("busStopForm", busStop);
        model.addObject("action", "Dodaj");
        model.setViewName("bus_stop_form");
        return model;
    }

    @RequestMapping(value = "/bus_stops/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("busStopForm") BusStop busStop) {
        busStopService.addBusStop(busStop);
        return new ModelAndView("redirect:/bus_stops");
    }

    @RequestMapping(value = "/bus_stop/{id}/timetable", method = RequestMethod.GET)
    public ModelAndView timetable(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        BusStop busStop = busStopService.getBusStopById(id);
        List<Timetable> timetables = timetableService.getTimetablesForBusStop(busStop);
        model.addObject("busStop", busStop);
        model.addObject("timetables", timetables);
        model.setViewName("timetable");
        return model;
    }

    @RequestMapping(value = "/bus_stop/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editBusStop(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        BusStop busStop = busStopService.getBusStopById(id);
        model.addObject("busStopForm", busStop);
        model.addObject("action", "Edytuj");
        model.setViewName("bus_stop_form");
        return model;
    }

    @RequestMapping(value = "/bus_stop/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        busStopService.deleteBusStop(id);
        return new ModelAndView("redirect:/bus_stops");
    }

}
