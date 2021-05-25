package com.koteckim.ztm.services;

import com.koteckim.ztm.model.*;
import com.koteckim.ztm.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.CollationElementIterator;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimetableServiceImplementation implements TimetableService {

    @Autowired
    BusStopOnRouteService busStopOnRouteService;

    @Autowired
    CourseService courseService;

    @Override
    public List<Timetable> getTimetablesForBusStop(BusStop busStop) {
        List<Timetable> timetables = new ArrayList<>();
        List<Integer> routeIds = busStopOnRouteService.getAllBusStopOnRoutes().stream()
                .filter(x -> x.getBusStop().getId() == busStop.getId())
                .map(y -> y.getRoute().getId()).distinct()
                .collect(Collectors.toList());
        Map<Route, List<Course>> routeCourseMap = courseService.getAllCoursesWithStops().stream()
                .filter(x -> routeIds.contains(x.getRoute().getId()))
                .collect(Collectors.groupingBy(Course::getRoute));
        routeCourseMap.forEach((x, y) -> {
            Timetable timetable = new Timetable();
            List<BusStop> busStops = y.get(0).getBusStops().stream()
                    .map(z -> z.getBusStopOnRoute().getBusStop())
                    .collect(Collectors.toList());

            List<Tuple<String, List<Tuple<String, List<String>>>>> timesListGrouped = new ArrayList<>();
            Map<String, Map<String, List<String>>> timesMap = new HashMap<>();
            timesMap.put("dni robocze", new HashMap<>());
            timesMap.put("soboty", new HashMap<>());
            timesMap.put("niedziele/święta", new HashMap<>());
            y.forEach(z -> {
                String[] timesT = z.getBusStops().stream()
                        .filter(q -> q.getBusStopOnRoute().getBusStop().getId() == busStop.getId())
                        .collect(Collectors.toList()).get(0).getTime().split(":");
                timesMap.get(z.getType()).computeIfAbsent(timesT[0], k -> new ArrayList<>());
                timesMap.get(z.getType()).get(timesT[0]).add(timesT[1]);
            });
            Arrays.asList("dni robocze", "soboty", "niedziele/święta").forEach(a -> {
                Map<String, List<String>> b = timesMap.get(a);
                Tuple<String, List<Tuple<String, List<String>>>> nt = new Tuple<>(a, new ArrayList<>());
                b.forEach((c, d) -> {
                    Tuple<String, List<String>> t = new Tuple<>(c, d.stream().sorted().collect(Collectors.toList()));
                    nt.getY().add(t);
                });
                List<Tuple<String, List<String>>> ntt = nt.getY();
                ntt = ntt.stream().sorted(Comparator.comparing(Tuple::getX)).collect(Collectors.toList());
                timesListGrouped.add(new Tuple<>(a, ntt));
            });

            timetable.setTimes(timesListGrouped);
            timetable.setStop(busStop);
            timetable.setRoute(x);
            timetable.setStops(busStops);
            timetables.add(timetable);
        });
        timetables.sort(Comparator.comparing(t -> t.getRoute().getName()));
        return timetables;
    }

}
