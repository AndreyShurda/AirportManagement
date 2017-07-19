package com.andrey.main.dl.dao;


import com.andrey.main.dl.models.Arrivals;
import org.apache.log4j.Logger;

import java.util.List;

import static com.andrey.main.dl.dao.HibernateDBUtil.operationCRUD;

public class ArrivalsDAO implements OperationDAO<Arrivals> {
    private static ArrivalsDAO instance = new ArrivalsDAO();
    private static final Logger log = Logger.getLogger(FlightDAO.class);

    private ArrivalsDAO() {
    }

    public static ArrivalsDAO getInstance() {
        return instance;
    }

    @Override
    public void add(Arrivals record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        Arrivals arrivals = getById(id);
        log.info("delete record: " + arrivals);
        operationCRUD(session -> session.delete(arrivals));
    }

    @Override
    public void update(Arrivals record) {
        log.info("update record: " + record);
        operationCRUD(session -> session.update(record));
    }

    @Override
    public Arrivals getById(long id) {
        final Arrivals[] arrivals = new Arrivals[1];
        operationCRUD(session -> {
            arrivals[0] = session.get(Arrivals.class, id);
        });
        return arrivals[0];
    }

    @Override
    public List<Arrivals> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Arrivals ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }
}
