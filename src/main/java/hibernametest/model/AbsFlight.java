package hibernametest.model;

import com.andrey.main.dl.data.FlightStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbsFlight implements Serializable{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    protected long id;

    protected LocalDateTime date;
    protected String city;
    protected char terminal;
    protected FlightStatus status;
    protected String gate;

//    @ManyToOne
//    private Flights flights;


//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

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
//
//    public Flights getFlights() {
//        return flights;
//    }
//
//    public void setFlights(Flights flights) {
//        this.flights = flights;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        AbsFlight absFlight = (AbsFlight) o;

        if (terminal != absFlight.terminal) return false;
        if (date != null ? !date.equals(absFlight.date) : absFlight.date != null) return false;
        if (city != null ? !city.equals(absFlight.city) : absFlight.city != null) return false;
        if (status != absFlight.status) return false;
        return gate != null ? gate.equals(absFlight.gate) : absFlight.gate == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (int) terminal;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (gate != null ? gate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbsFlight{" +
                "date=" + date +
                ", city='" + city + '\'' +
                ", terminal=" + terminal +
                ", status=" + status +
                ", gate='" + gate + '\'' +
                '}';
    }
}
