package com.andrey.main.dl.dao;


import com.andrey.main.dl.dao.utils.InitialData;
import com.andrey.main.dl.models.Arrivals;
import com.andrey.main.dl.models.Departures;
import com.andrey.main.dl.models.Destination;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;

import java.util.List;

import static com.andrey.main.dl.dao.utils.HibernateDBUtil.operationCRUD;

public class DestinationDAO {
    private static DestinationDAO instance = new DestinationDAO();
    private static String TABLE = InitialData.TABLE_ARRIVALS;
    private static final Logger log = Logger.getLogger(DestinationDAO.class);

    private DestinationDAO() {
    }

    public static DestinationDAO getInstance() {
        return instance;
    }

    public static void setTABLE(String TABLE) {
        DestinationDAO.TABLE = TABLE;
    }


    public void add(Destination record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(TABLE, record));
    }


    public void delete(Destination destination) {
        log.info("delete record: " + destination);
        operationCRUD(session -> session.delete(destination));
    }


    public void update(Destination record) {
        log.info("update record: " + record);
        operationCRUD(session -> session.update(record));
    }


    public Destination getById(Class clazz, long id) {
        final Destination[] destinations = new Destination[1];
        operationCRUD(session -> {
            destinations[0] = (Destination) session.get(clazz, id);
        });
        return destinations[0];
    }

    public List<Destination> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM " + TABLE).list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }


    public List<Destination> searchByNumber(String text) {
        if (TABLE.equals(InitialData.TABLE_ARRIVALS)) {
            return searchByNumber(Arrivals.class, text);
        }
        if (TABLE.equals(InitialData.TABLE_DEPARTURES)) {
            return searchByNumber(Departures.class, text);
        }

        return null;
    }

    private List<Destination> searchByNumber(Class clazz, String text) {
        final List<Destination>[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createCriteria(clazz)
                    .createAlias("flight", "flight")
                    .add(Restrictions.like("flight.number", text+"%"))
                    .list();
        });
        return list[0];
    }

    public List<Destination> searchByCity(String text) {
        if (TABLE.equals(InitialData.TABLE_ARRIVALS)) {
            return searchByCity(Arrivals.class, text);
        } else if (TABLE.equals(InitialData.TABLE_DEPARTURES)) {
            return searchByCity(Departures.class, text);
        } else
            return null;
    }


    public List<Destination> searchByCity(Class clazz, String text) {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createCriteria(clazz)
                    .add(Restrictions.like("city", text + "%"))
                    .list();
        });
        return list[0];
    }

}
