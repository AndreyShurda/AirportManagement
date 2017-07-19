package com.andrey.main.dl.models;


import javax.persistence.*;
import java.io.Serializable;

//@EntityListeners({Destination.class})
@Entity
public class Departures extends Destination implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne
//    private Flight flight;
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public Flight getDestination() {
//        return flight;
//    }
//
//    public void setDestination(Flight flight) {
//        this.flight = flight;
//    }
//
//    @Override
//    public String toString() {
//        return "Departures{" +
//                "id=" + id +
//                ", flight=" + flight +
//                "} " + super.toString();
//    }
}

