package com.andrey.main.dl.dao;

import com.andrey.main.dl.models.Flight;
import org.apache.log4j.Logger;
import java.util.List;
import static com.andrey.main.dl.dao.HibernateDBUtil.operationCRUD;

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
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        Flight flight = getById(id);
        log.info("delete record: " + flight);
        operationCRUD(session -> session.delete(flight));
    }

    @Override
    public void update(Flight record) {
        log.info("update record: " + record);
        operationCRUD(session -> session.update(record));
    }


    @Override
    public Flight getById(long id) {
        final Flight[] flight = new Flight[1];
        operationCRUD(session -> {
            flight[0] = session.get(Flight.class, id);
        });
        return flight[0];
    }

    @Override
    public List<Flight> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Flight").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }

    public List<Flight> searchByNumber(String text) {
        return searchByColumn("number", text);
    }

    public List<Flight> searchByCity(String text) {
        return searchByColumn("city", text);
    }

    private List<Flight> searchByColumn(String columnName, String value) {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Flight where " + columnName + " like '" + value + "%'").list();
        });
        log.info("searchBy" + columnName + ": " + list[0]);
        return list[0];
    }
}
