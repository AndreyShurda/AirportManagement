package com.andrey.main.dl.dao;

import com.andrey.main.dl.models.Ticket;
import org.apache.log4j.Logger;

import java.util.List;

import static com.andrey.main.dl.dao.utils.HibernateDBUtil.operationCRUD;


public class TicketDAO implements OperationDAO<Ticket> {
    private static TicketDAO instance = new TicketDAO();
    private static final Logger log = Logger.getLogger(TicketDAO.class);

    private TicketDAO() {
    }

    public static TicketDAO getInstance() {
        return instance;
    }

    @Override
    public void add(Ticket record) {
        log.info("add record: " + record);
        operationCRUD(session -> session.save(record));
    }

    @Override
    public void delete(long id) {
        Ticket ticket = getById(id);
        log.info("delete record: " + ticket);
        operationCRUD(session -> session.delete(ticket));
    }

    @Override
    public void update(Ticket record) {
        log.info("update record: " + record);
        operationCRUD(session -> session.update(record));
    }


    @Override
    public Ticket getById(long id) {
        final Ticket[] ticket = new Ticket[1];
        operationCRUD(session -> {
            ticket[0] = session.get(Ticket.class, id);
        });
        return ticket[0];
    }

    @Override
    public List<Ticket> getAll() {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Ticket ").list();
        });
        log.info("getAll records: " + list[0]);
        return list[0];
    }

    public List<Ticket> searchByPrice(double from, double to) {
        final List[] list = new List[1];
        operationCRUD(session -> {
            list[0] = session.createQuery("FROM Ticket where price>=" + from + " and price<=" + to).list();
        });
        log.info("searchByPrice: " + list[0]);
        return list[0];
    }
}
