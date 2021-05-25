package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.Bus;
import com.koteckim.ztm.services.BusService;
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
public class BusController {

    @Autowired
    BusService busService;

    @RequestMapping(value = "/buses", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("buses");
        List<Bus> buses = busService.getAllBuses();
        model.addObject("buses", buses);
        return model;
    }

    @RequestMapping(value = "/bus/add", method = RequestMethod.GET)
    public ModelAndView addBus() {
        ModelAndView model = new ModelAndView();
        Bus bus = new Bus();
        model.addObject("busForm", bus);
        model.addObject("action", "Dodaj");
        model.setViewName("bus_form");
        return model;
    }

    @RequestMapping(value = "/bus/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("busForm") Bus bus) {
        busService.addBus(bus);
        return new ModelAndView("redirect:/buses");
    }

    @RequestMapping(value = "/bus/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editBus(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        Bus bus = busService.getBusById(id);
        model.addObject("busForm", bus);
        model.addObject("action", "Edytuj");
        model.setViewName("bus_form");
        return model;
    }

    @RequestMapping(value = "/bus/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        busService.deleteBus(id);
        return new ModelAndView("redirect:/buses");
    }

}
