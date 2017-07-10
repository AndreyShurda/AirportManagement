package com.andrey.main.bl.services;

import com.andrey.main.dl.dao.TicketDAO;
import com.andrey.main.dl.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService implements ServiceOperation<Ticket> {
    private TicketDAO ticketDAO = TicketDAO.getInstance();

    @Override
    public void add(Ticket record) {
        ticketDAO.add(record);
    }

    @Override
    public void delete(Ticket record) {
        ticketDAO.delete(record.getIdTicket());
    }

    @Override
    public void update(Ticket record) {
        ticketDAO.update(record);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDAO.getAll();
    }

    public List<Ticket> searchByPrice(double from, double to) {
        return ticketDAO.searchByPrice(from, to);
    }

}
