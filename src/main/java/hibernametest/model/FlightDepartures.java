package hibernametest.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FlightDepartures extends AbsFlight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @ManyToOne
    private Flights flights;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Flights getFlights() {
        return flights;
    }

    public void setFlights(Flights flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "FlightDepartures{" +
                "id=" + id +
                "date=" + date +
                ", city='" + city + '\'' +
                ", terminal=" + terminal +
                ", status=" + status +
                ", gate='" + gate + '\'' +
                '}';
    }
}
