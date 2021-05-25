package com.koteckim.ztm.model;

import javax.persistence.*;

@Entity
@Table(name = "bus_stops_on_course")
public class BusStopOnCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "bus_stop_on_route_id")
    private BusStopOnRoute busStopOnRoute;

    @Column(name = "time")
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public BusStopOnRoute getBusStopOnRoute() {
        return busStopOnRoute;
    }

    public void setBusStopOnRoute(BusStopOnRoute busStopOnRoute) {
        this.busStopOnRoute = busStopOnRoute;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
