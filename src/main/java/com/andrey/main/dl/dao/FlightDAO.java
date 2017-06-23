package com.andrey.main.dl.dao;

import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Flight;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.andrey.main.dl.dao.DBUtil.*;
//import static com.andrey.main.dl.dao.InitialData.TABLE;

public class FlightDAO implements OperationDAO<Flight> {
    private static FlightDAO instance = new FlightDAO();
    private static String TABLE = InitialData.TABLE_ARRIVALS;
    private static final Logger log = Logger.getLogger(FlightDAO.class);

    private FlightDAO() {
    }

    public static FlightDAO getInstance() {
        return instance;
    }

    public static void setTABLE(String TABLE) {
        FlightDAO.TABLE = TABLE;
    }

    @Override
    public void add(Flight record) {
        String query = "insert into " + TABLE + " (number, date, city," +
                " terminal, flightStatus, gate) values(?,?,?,?,?,?)";
        doQuery(record, query);
        log.info("Add record: " + record);
    }

    @Override
    public void delete(Flight record) {
        createConnection();
        String query = "delete from " + TABLE + " WHERE idFlight =?";
        try {
            executeQuery(query, String.valueOf(record.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        log.info("Delete record: " + record);
    }

    @Override
    public void update(Flight record) {
        String query = "UPDATE " + TABLE + " SET number=?, date=?, city=?," +
                "terminal=?, flightStatus=?, gate=? WHERE  idFlight =" + record.getId();
        doQuery(record, query);
        log.info("Update record: " + record);
    }


    private void doQuery(Flight record, String query) {
        createConnection();
        try {
            executeQuery(query,
                    record.getNumber(),
                    record.getDate().toString(),
                    record.getCity(),
                    String.valueOf(record.getTerminal()),
                    String.valueOf(record.getStatus().ordinal()),
                    record.getGate().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public List<Flight> getAll() {
        String query = "select idFlight, number, date, city, terminal, flightStatus, gate " +
                "from " + TABLE;

        log.info("Get all record from table: " + TABLE);
        return getFlights(query);
    }

    public List<Flight> searchByNumber(String value) {
        return searchByColumn("number", value);
    }

    public List<Flight> searchByCity(String text) {
        return searchByColumn("city", text);
    }

    private List<Flight> searchByColumn(String columnName, String text) {
        String query = "select idFlight, number, date, city, terminal, flightStatus, gate " +
                "from " + TABLE +
                " where " + columnName + " like '" + text + "%'";
        return getFlights(query);
    }

    private List<Flight> getFlights(String query) {
        createConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = executeQuery(query);


            while (resultSet.next()) {
                int idFlight = Integer.valueOf(resultSet.getString("idFlight"));
                String number = resultSet.getString("number");
                Timestamp date = resultSet.getTimestamp("date");
                String city = resultSet.getString("city");
                char terminal = resultSet.getString("terminal").charAt(0);
                FlightStatus flightStatus = FlightStatus.values()[resultSet.getInt("flightStatus")];
                String gate = resultSet.getString("gate");
                flights.add(new Flight(idFlight,
                        number,
                        date.toLocalDateTime(),
                        city,
                        terminal,
                        flightStatus,
                        gate)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return flights;
    }
}
