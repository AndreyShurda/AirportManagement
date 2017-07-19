package hibernametest.model;

import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.data.FlightType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class FlightArivalls extends AbsFlight implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime date;
    private String city;
    private char terminal;
    private FlightStatus status;
    private String gate;
    private FlightType flightType;

//    @ManyToOne
    private Flights flights;

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

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

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
        return "FlightArivalls{" +
                "id=" + id +
                ", date=" + date +
                ", city='" + city + '\'' +
                ", terminal=" + terminal +
                ", status=" + status +
                ", gate='" + gate + '\'' +
                ", flightType=" + flightType +
                ", flights=" + flights +
                '}';
    }
}
