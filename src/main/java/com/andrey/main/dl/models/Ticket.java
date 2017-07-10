package com.andrey.main.dl.models;

import com.andrey.main.dl.data.ClassType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTicket;
    private double price;
    private ClassType classType;


    @ManyToOne
    private Flight flight;

    public Ticket() {
    }

//    public Ticket(String flight, double price, ClassType classType) {
//        this.flight = flight;
//        this.price = price;
//        this.classType = classType;
//    }
//
//    public Ticket(long idTicket, String flight, double price, ClassType classType) {
//        this(flight, price, classType);
//        this.idTicket = idTicket;
//    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (idTicket != ticket.idTicket) return false;
        if (Double.compare(ticket.price, price) != 0) return false;
        return classType == ticket.classType;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idTicket ^ (idTicket >>> 32));
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", price=" + price +
                ", classType=" + classType +
                ", flight=" + flight +
                '}';
    }
}
