package com.andrey.main.dl.dao;

import com.andrey.main.dl.models.Passenger;
import org.apache.log4j.Logger;
import java.util.List;
import static com.andrey.main.dl.dao.HibernateDBUtil.operationCRUD;

public class PassengerDAO implements OperationDAO<Passenger> {
    private static PassengerDAO instance = new PassengerDAO();
    private static final Logger log = Logger.getLogger(PassengerDAO.class);

    private PassengerDAO() {
    }

    public static PassengerDAO getInstance() {
        return instance;
    }

    @Override
    public void add(Passenger record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        Passenger passenger = getById(id);
        log.info("add record: " + passenger);
        operationCRUD(session -> {
            session.delete(passenger);
        });
    }

    @Override
    public void update(Passenger record) {
        log.info("add update: " + record);
        operationCRUD(session -> session.update(record));
    }


    @Override
    public Passenger getById(long id) {
        final Passenger[] passenger = new Passenger[1];
        operationCRUD(session -> {
            passenger[0] = session.get(Passenger.class, id);
        });
        return passenger[0];
    }

    @Override
    public List<Passenger> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Passenger ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }

    public List<Passenger> searchByFirstName(String text) {
        return searchByColumn("firstName", text);
    }

    public List<Passenger> searchByLastName(String text) {
        return searchByColumn("lastName", text);
    }

    public List<Passenger> searchByPassport(String text) {
        return searchByColumn("passport", text);
    }

    private List<Passenger> searchByColumn(String columnName, String value) {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Passenger where " + columnName + " like '" + value + "%'").list();
        });
        log.info("searchBy" + columnName + ": " + list[0]);
        return list[0];
    }
}
