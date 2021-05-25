package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.Driver;
import com.koteckim.ztm.services.DriverService;
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
public class DriverController {

    @Autowired
    DriverService driverService;

    @RequestMapping(value = "/drivers", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("drivers");
        List<Driver> drivers = driverService.getAllDrivers();
        model.addObject("drivers", drivers);
        return model;
    }

    @RequestMapping(value = "/driver/add", method = RequestMethod.GET)
    public ModelAndView addDriver() {
        ModelAndView model = new ModelAndView();
        Driver driver = new Driver();
        model.addObject("driverForm", driver);
        model.addObject("action", "Dodaj");
        model.setViewName("driver_form");
        return model;
    }

    @RequestMapping(value = "/driver/add", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("driverForm") Driver driver) {
        driverService.addDriver(driver);
        return new ModelAndView("redirect:/drivers");
    }

    @RequestMapping(value = "/driver/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editDriver(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        Driver driver = driverService.getDriverById(id);
        model.addObject("driverForm", driver);
        model.addObject("action", "Edytuj");
        model.setViewName("driver_form");
        return model;
    }

    @RequestMapping(value = "/driver/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        driverService.deleteDriver(id);
        return new ModelAndView("redirect:/drivers");
    }

}
