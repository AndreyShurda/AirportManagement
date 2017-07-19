package hibernametest.model;

import hibernametest.dao.Tickets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Flights implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, mappedBy = "flights")
    private List<Tickets> ticketsList = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL, mappedBy = "flights")
//    private List<FlightArivalls> flightArivallses = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL, mappedBy = "flights")
//    private List<FlightDepartures> flightDepartures  = new ArrayList<>();

    public Flights() {
    }

    public Flights(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

//    public List<FlightArivalls> getFlightArivallses() {
//        return flightArivallses;
//    }
//
//    public void setFlightArivallses(List<FlightArivalls> flightArivallses) {
//        this.flightArivallses = flightArivallses;
//    }
//
//    public List<FlightDepartures> getFlightDepartures() {
//        return flightDepartures;
//    }
//
//    public void setFlightDepartures(List<FlightDepartures> flightDepartures) {
//        this.flightDepartures = flightDepartures;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flights flights = (Flights) o;

        if (id != flights.id) return false;
        return number != null ? number.equals(flights.number) : flights.number == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", number='" + number + '\'' +
//                ", ticketsList=" + ticketsList +
                '}';
    }
}