package com.koteckim.ztm.model;

import java.util.List;

public class Timetable {

    private Route route;
    private BusStop stop;
    private List<Tuple<String, List<Tuple<String, List<String>>>>> times;
    private List<BusStop> stops;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public BusStop getStop() {
        return stop;
    }

    public void setStop(BusStop stop) {
        this.stop = stop;
    }


    public List<BusStop> getStops() {
        return stops;
    }

    public void setStops(List<BusStop> stops) {
        this.stops = stops;
    }

    public List<Tuple<String, List<Tuple<String, List<String>>>>> getTimes() {
        return times;
    }

    public void setTimes(List<Tuple<String, List<Tuple<String, List<String>>>>> times) {
        this.times = times;
    }

}



