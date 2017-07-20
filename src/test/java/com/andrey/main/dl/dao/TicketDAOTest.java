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

    @Test
    public void delete() throws Exception {
        List<Ticket> tickets = ticketDAO.getAll();
        for (Ticket ticket : tickets) {
            ticketDAO.delete(ticket.getIdTicket());
        }

        assertTrue(ticketDAO.getAll().size() == 0);
    }


}