package com.andrey.main.dl.models;

import com.andrey.main.dl.data.FlightStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    //    private Airplane airplane;
    private LocalDateTime date;
    private String city;
    //    private String to;
    private char terminal;
    private FlightStatus status;
    private String gate;
//    private Ticket ticket;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Ticket> ticketList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flight")
    private List<Passenger> passengerList = new ArrayList<>();


    public Flight() {
    }

    public Flight(String number, LocalDateTime date, String city, char terminal, FlightStatus status, String gate) {
        this.number = number;
        this.date = date;
        this.city = city;
        this.terminal = terminal;
        this.status = status;
        this.gate = gate;
    }

    public Flight(long id, String number, LocalDateTime date, String city, char terminal, FlightStatus status, String gate) {
        this(number, date, city, terminal, status, gate);
        this.id = id;
    }


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

    public char getTerminal() {
        return terminal;
    }

    public void setTerminal(char terminal) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (terminal != flight.terminal) return false;
        if (number != null ? !number.equals(flight.number) : flight.number != null) return false;
        if (date != null ? !date.equals(flight.date) : flight.date != null) return false;
        if (city != null ? !city.equals(flight.city) : flight.city != null) return false;
        if (status != flight.status) return false;
        return gate != null ? gate.equals(flight.gate) : flight.gate == null;

    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) terminal;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (gate != null ? gate.hashCode() : 0);
        return result;
    }
}
