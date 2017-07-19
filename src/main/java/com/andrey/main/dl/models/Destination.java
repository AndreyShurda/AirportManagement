package com.andrey.main.dl.models;

import com.andrey.main.dl.data.FlightStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class Destination implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected LocalDateTime date;
    protected String city;
    protected char terminal;
    protected FlightStatus status;
    protected String gate;

    @ManyToOne
    private Flight flight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Destination that = (Destination) o;

        if (terminal != that.terminal) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (status != that.status) return false;
        return gate != null ? gate.equals(that.gate) : that.gate == null;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) terminal;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (gate != null ? gate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
//                "Destination{" +
                "id=" + id +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", terminal=" + terminal +
                ", status=" + status +
                ", gate='" + gate + '\'' +
                ", flight=" + flight ;
//                '}';
    }
}
