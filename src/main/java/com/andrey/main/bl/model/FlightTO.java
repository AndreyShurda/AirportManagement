package com.andrey.main.bl.model;


import com.andrey.main.dl.data.FlightStatus;

import java.time.LocalDateTime;

public class FlightTO {
    private long id;
    private String number;
    private LocalDateTime date;
    private String city;
    private Character terminal;
    private FlightStatus status;
    private String gate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Character getTerminal() {
        return terminal;
    }

    public void setTerminal(Character terminal) {
        this.terminal = terminal;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return "FlightTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", terminal=" + terminal +
                ", status=" + status +
                ", gate='" + gate + '\'' +
                '}';
    }
}
