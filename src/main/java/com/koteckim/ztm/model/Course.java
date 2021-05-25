package com.koteckim.ztm.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "type")
    private String type;

    @Transient
    private List<BusStopOnCourse> busStops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BusStopOnCourse> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStopOnCourse> busStops) {
        this.busStops = busStops;
    }

    public String getRouteIdCourseId() {
        return "linia " + route.getName() + " kurs " + id;
    }
}
