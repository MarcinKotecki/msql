package com.koteckim.ztm.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Transient
    private List<BusStopOnRoute> busStops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BusStopOnRoute> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStopOnRoute> busStops) {
        this.busStops = busStops;
    }
}
