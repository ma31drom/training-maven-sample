package com.epam;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class CollectionsSample {

    private Map<Person, List<Schedule>> schedules;

    @Test
    public void init() throws IOException {
        Properties properties = new Properties();

        try (InputStream inStream = getClass().getClassLoader().getResourceAsStream("timePeriods.properties")) {
            properties.load(inStream);
        }

        schedules = properties.entrySet().stream()
                .map(this::convertToPersonToSchedulePair)
                .collect(
                        Collectors.groupingBy(
                                CollectionsSample.Pair::getFirst,
                                Collectors.mapping(Pair::getSecond, Collectors.toList())));


        //Find which daypart for Maksim on 10.AM

        Person p = Person.MAKSIM;
        LocalTime of = LocalTime.of(8, 01);

        System.out.println(getMessage(p, of, getDayPartForPersonOnTime(p, of)));

        System.out.println(getMessage(Person.BAZHENA, LocalTime.of(8, 01), getDayPartForPersonOnTime(Person.BAZHENA, LocalTime.of(8, 01))));
    }

    private String getMessage(Person p, LocalTime of, DayPart dayPart) {
        return p.name() + " has " + dayPart.name() + " on " + of.toString();
    }

    private DayPart getDayPartForPersonOnTime(Person p, LocalTime of) {
        return schedules.get(p)
                .stream()
                .filter(schedule -> schedule.matches(of)).map(Schedule::getDaypart)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("there is no day period for " + p.name() + " on " + of.toString()));
    }

    private Pair convertToPersonToSchedulePair(Map.Entry<Object, Object> entry) {
        String key = entry.getKey().toString();
        String[] split = key.split("\\.");
        Person person = Person.valueOf(split[0].toUpperCase());
        Schedule schedule = getSchedule(entry, split);
        return new Pair(person, schedule);
    }

    private Schedule getSchedule(Map.Entry<Object, Object> entry, String[] split) {
        DayPart dayPart = DayPart.valueOf(split[1].toUpperCase());

        Schedule schedule = new Schedule(dayPart);
        String value = entry.getValue().toString();

        String[] split1 = value.split("-");

        LocalTime start = LocalTime.parse(split1[0], DateTimeFormatter.ofPattern("hh:mma"));
        LocalTime end = LocalTime.parse(split1[1], DateTimeFormatter.ofPattern("hh:mma"));

        generateTimePeriods(schedule, start, end);
        return schedule;
    }

    private void generateTimePeriods(Schedule schedule, LocalTime start, LocalTime end) {
        if (start.isBefore(end)) {
            schedule.addToPeriods(new TimePeriod(start, end));
        } else {
            schedule.addToPeriods(new TimePeriod(LocalTime.of(0, 0, 0), end));
            schedule.addToPeriods(new TimePeriod(start, LocalTime.of(23, 59, 59)));
        }
    }


    public static class Pair {
        Person first;
        Schedule second;

        public Pair(Person first, Schedule second) {
            this.first = first;
            this.second = second;
        }

        public Person getFirst() {
            return first;
        }

        public Schedule getSecond() {
            return second;
        }

    }
}
