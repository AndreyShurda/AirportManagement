package com.andrey.main.dl.models;


import com.andrey.main.dl.data.MyDayOfWeek;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class Schedule {
    private String flightNumber;
    private List<DayOfWeek> days;
    private LocalTime time;

    public Schedule(String flightNumber, List<DayOfWeek> days, LocalTime time) {
        this.flightNumber = flightNumber;
        this.days = days;
        this.time = time;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "flightNumber='" + flightNumber + '\'' +
                ", days=" + days +
                ", time=" + time +
                '}';
    }
}
