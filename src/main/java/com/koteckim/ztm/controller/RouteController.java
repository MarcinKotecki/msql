package com.koteckim.ztm.controller;

import com.koteckim.ztm.model.BusStop;
import com.koteckim.ztm.model.BusStopOnRoute;
import com.koteckim.ztm.model.Route;
import com.koteckim.ztm.services.BusStopOnRouteService;
import com.koteckim.ztm.services.BusStopService;
import com.koteckim.ztm.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "")
public class RouteController {

    @Autowired
    RouteService routeService;

    @Autowired
    BusStopService busStopService;

    @Autowired
    BusStopOnRouteService busStopOnRouteService;

    @RequestMapping(value = "/routes", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("routes");
        List<Route> routes = routeService.getAllRoutesWithStops();
        model.addObject("routes", routes);
        return model;
    }

    @RequestMapping(value = "/routes/add", method = RequestMethod.GET)
    public ModelAndView addRoute() {
        ModelAndView model = new ModelAndView();
        model.addObject("action", "Dodaj");
        List<BusStop> busStops = busStopService.getAllBusStops();
        System.out.println("aaa " + busStops.toString());
        model.addObject("busStops",busStops);
        model.setViewName("route_form");
        return model;
    }

    @RequestMapping(value = "/routes/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();

        Route route = new Route();
        route.setName(params.get("name")[0]);
        route.setType(params.get("type")[0]);
        routeService.addRoute(route);


        params.forEach((a,b) -> {
            if (a.contains("busStop")) {
                BusStopOnRoute busStopOnRoute = new BusStopOnRoute();
                int c = Integer.parseInt(a.substring(7));
                int d = Integer.parseInt(b[0]);
                busStopOnRoute.setRoute(route);
                busStopOnRoute.setOrder(c);
                busStopOnRoute.setBusStop(busStopService.getBusStopById(d));
                busStopOnRouteService.addBusStopOnRoute(busStopOnRoute);
            }
        });

        return new ModelAndView("redirect:/routes");
    }

    @RequestMapping(value = "/route/{id}/edit", method = RequestMethod.GET)
    public ModelAndView editRoute(@PathVariable int id) {
        ModelAndView model = new ModelAndView();
        Route route = routeService.getRouteById(id);
        model.addObject("routeForm", route);
        model.addObject("action", "Edytuj");
        model.setViewName("route_form");
        return model;
    }

    @RequestMapping(value = "/route/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        routeService.deleteRoute(id);
        return new ModelAndView("redirect:/routes");
    }

}
