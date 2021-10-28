package com.epam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private DayPart daypart;
    private List<TimePeriod> periods = new ArrayList<>();

    public Schedule(DayPart daypart) {
        this.daypart = daypart;
    }

    public DayPart getDaypart() {
        return daypart;
    }

    public void setDaypart(DayPart daypart) {
        this.daypart = daypart;
    }

    public void addToPeriods(TimePeriod period) {
        periods.add(period);
    }

    public boolean matches(LocalTime time) {
        return periods
                .stream()
                .anyMatch(timePeriod -> timePeriod.getStart().isBefore(time) && timePeriod.getEnd().isAfter(time));
    }
}
