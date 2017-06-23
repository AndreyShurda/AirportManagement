package com.andrey.main.dl.dao;

import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.andrey.main.dl.dao.DBUtil.*;
import static com.andrey.main.dl.dao.InitialData.*;


public class TicketDAO implements OperationDAO<Ticket> {
    private static TicketDAO instance = new TicketDAO();
    //    private static final String TABLE_TICKETS = "tickets";
//    private static final String TABLE_TICKETS = TABLE_TICKETS;

    private TicketDAO() {
    }

    public static TicketDAO getInstance() {
        return instance;
    }


    @Override
    public void add(Ticket record) {
        String query = "insert into " + TABLE_TICKETS + " (idFlight, price, classType)" +
                " values(?,?,?)";
        doQuery(record, query);
    }

    @Override
    public void delete(Ticket record) {
        createConnection();
        String query = "delete from " + TABLE_TICKETS + " WHERE idTicket =?";
        try {
            executeQuery(query, String.valueOf(record.getIdTicket()));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Ticket record) {
        String query = "UPDATE " + TABLE_TICKETS + " SET idFlight=?, price=?, classType=? WHERE  idTicket =" + record.getIdTicket();
        doQuery(record, query);
    }

    @Override
    public List<Ticket> getAll() {
        String query = "SELECT t.idTicket, f.number, t.price, t.classType " +
                "FROM " + DATABASE + "." + TABLE_ARRIVALS + " f " +
                "inner join " + DATABASE + "." + TABLE_TICKETS + " t on f.idFlight = t.idFlight";
        return getAll(query);
    }

    public List<Ticket> searchByPrice(double from, double to) {
        TicketDAO ticketDAO = TicketDAO.getInstance();
        String query = "SELECT t.idTicket, f.number, t.price, t.classType " +
                "FROM " + DATABASE + "." + TABLE_ARRIVALS + " f " +
                "inner join " + DATABASE + "." + TABLE_TICKETS + " t on f.idFlight = t.idFlight " +
                "where t.price > " + from + " and t.price < " + to;
        return ticketDAO.getAll(query);
    }

    public List<Ticket> getAll(String query) {
        createConnection();
        List<Ticket> tickets = new ArrayList<>();
//        String query = "select idTicket, idFlight, price, classType " +
//                "from " + TABLE_TICKETS;


//        String query = "SELECT t.idTicket, f.number, t.price, t.classType " +
//                "FROM airport.flight f " +
//                "inner join airport.tickets t on f.idFlight = t.idFlight";
        try {
            ResultSet resultSet = executeQuery(query);

            while (resultSet.next()) {
                Integer idTicket = Integer.valueOf(resultSet.getString("idTicket"));
                String numberFlight = resultSet.getString("number");
                float price = resultSet.getFloat("price");
                ClassType classType = ClassType.values()[resultSet.getInt("classType")];

                tickets.add(new Ticket(idTicket,
                        numberFlight,
                        price,
                        classType)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return tickets;
    }

    private void doQuery(Ticket record, String query) {
        createConnection();
        try {
            executeQuery(query,
                    record.getIdFlight(),
                    String.valueOf(record.getPrice()),
                    String.valueOf(record.getClassType().ordinal())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }


}
