package com.andrey.main.dl.models;

import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.data.FlightType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
//    private LocalDateTime date;
//    private String city;
//    private char terminal;
//    private FlightStatus status;
//    private String gate;
//    private FlightType flightType;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Ticket> ticketList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Passenger> passengerList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Arrivals> arrivalsList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Departures> departuresList = new ArrayList<>();



//    public Flight(String number, LocalDateTime date, String city, char terminal, FlightStatus status, String gate) {
//        this.number = number;
//        this.date = date;
//        this.city = city;
//        this.terminal = terminal;
//        this.status = status;
//        this.gate = gate;
//    }

//    public Flight(long id, String number, LocalDateTime date, String city, char terminal, FlightStatus status, String gate) {
//        this(number, date, city, terminal, status, gate);
//        this.id = id;
//    }


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

//    public LocalDateTime getDate() {
//        return date;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public char getTerminal() {
//        return terminal;
//    }
//
//    public void setTerminal(char terminal) {
//        this.terminal = terminal;
//    }
//
//    public FlightStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(FlightStatus status) {
//        this.status = status;
//    }
//
//    public String getGate() {
//        return gate;
//    }
//
//    public void setGate(String gate) {
//        this.gate = gate;
//    }

//    public FlightType getFlightType() {
//        return flightType;
//    }
//
//    public void setFlightType(FlightType flightType) {
//        this.flightType = flightType;
//    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public List<Arrivals> getArrivalsList() {
        return arrivalsList;
    }

    public void setArrivalsList(List<Arrivals> arrivalsList) {
        this.arrivalsList = arrivalsList;
    }

    public List<Departures> getDeparturesList() {
        return departuresList;
    }

    public void setDeparturesList(List<Departures> departuresList) {
        this.departuresList = departuresList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        return number != null ? number.equals(flight.number) : flight.number == null;

    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }

    @Override
    public String toString() {
//        return "Flight{" +
//                "id=" + id +
//                ", number='" + number + '\'' +
//                ", date=" + date +
//                ", city='" + city + '\'' +
//                ", terminal=" + terminal +
//                ", status=" + status +
//                ", gate='" + gate + '\'' +
//                '}';
        return number;
    }

}
