package com.andrey.main.dl.dao;

import com.andrey.main.dl.models.Departures;
import org.apache.log4j.Logger;
import java.util.List;

import static com.andrey.main.dl.dao.HibernateDBUtil.operationCRUD;

public class DeparturesDAO implements OperationDAO<Departures>{
    private static DeparturesDAO instance = new DeparturesDAO();
    private static final Logger log = Logger.getLogger(FlightDAO.class);

    private DeparturesDAO() {
    }

    public static DeparturesDAO getInstance() {
        return instance;
    }

    @Override
    public void add(Departures record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        Departures departures = getById(id);
        log.info("delete record: " + departures);
        operationCRUD(session -> session.delete(departures));
    }

    @Override
    public void update(Departures record) {
        log.info("update record: " + record);
        operationCRUD(session -> session.update(record));
    }

    @Override
    public Departures getById(long id) {
        final Departures[] Departures = new Departures[1];
        operationCRUD(session -> {
            Departures[0] = session.get(Departures.class, id);
        });
        return Departures[0];
    }

    @Override
    public List<Departures> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Departures ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }

}
