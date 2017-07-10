package hibernametest.dao;

import hibernametest.model.Flights;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Tickets implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

//    @Column(name = "price")
    private Double price;

//    @Column(name = "classType")
    private Integer classType;

    @ManyToOne
    private Flights flights;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

     public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Flights getFlights() {
        return flights;
    }

    public void setFlights(Flights flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tickets tickets = (Tickets) o;

        if (idTicket != tickets.idTicket) return false;
        if (price != null ? !price.equals(tickets.price) : tickets.price != null) return false;
        if (classType != null ? !classType.equals(tickets.classType) : tickets.classType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTicket;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (classType != null ? classType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "idTicket=" + idTicket +
                ", price=" + price +
                ", classType=" + classType +
                ", flight=" + flights +
                '}';
    }
}
