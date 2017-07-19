package com.andrey.main.dl.dao;

import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Ticket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TicketDAOTest {
    private TicketDAO ticketDAO;

    @Before
    public void setUp() throws Exception {
        ticketDAO = TicketDAO.getInstance();
    }

    @Test
    public void add() throws Exception {
        Ticket ticketExpected = new Ticket();
        ticketExpected.setClassType(ClassType.BUSINESS);
        ticketExpected.setPrice(45.5);

        ticketDAO.add(ticketExpected);
        Ticket ticketActual = ticketDAO.getAll().get(0);

        assertEquals(ticketExpected, ticketActual);
    }

//    @Test
//    public void update() throws Exception {
//        Ticket ticket = new Ticket();
//        ticket.setClassType(ClassType.BUSINESS);
//        ticket.setPrice(111.5);
//        ticketDAO.add(ticket);
//
//        List<Ticket> tickets = ticketDAO.getAll();
//        Ticket ticketExpected = tickets.get(tickets.size() - 1);
//
//        ticketExpected.setPrice(222.15);
//        ticketDAO.update(ticketExpected);
//
//        Ticket ticketActual = ticketDAO.getAll().get(0);
//        assertTrue(ticketActual.getPrice() == ticketExpected.getPrice());
//    }

    @Test
    public void delete() throws Exception {
        List<Ticket> tickets = ticketDAO.getAll();
        for (Ticket ticket : tickets) {
            ticketDAO.delete(ticket.getIdTicket());
        }

        assertTrue(ticketDAO.getAll().size() == 0);
    }

//    public List<Ticket>


}