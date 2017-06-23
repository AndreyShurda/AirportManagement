package com.andrey.main.dl.models;

import com.andrey.main.dl.data.ClassType;

import java.time.LocalDateTime;

public class Ticket {
    private long idTicket;
    private String idFlight;
    private double price;
    private ClassType classType;

    public Ticket() {
    }

    public Ticket(String idFlight, double price, ClassType classType) {
        this.idFlight = idFlight;
        this.price = price;
        this.classType = classType;
    }

    public Ticket(long idTicket, String idFlight, double price, ClassType classType) {
        this(idFlight, price, classType);
        this.idTicket = idTicket;
    }

    public long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(long idTicket) {
        this.idTicket = idTicket;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(String idFlight) {
        this.idFlight = idFlight;
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
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", idFlight='" + idFlight + '\'' +
                ", price=" + price +
                ", classType=" + classType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (Double.compare(ticket.price, price) != 0) return false;
        if (idFlight != null ? !idFlight.equals(ticket.idFlight) : ticket.idFlight != null) return false;
        return classType == ticket.classType;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idFlight != null ? idFlight.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        return result;
    }
}
