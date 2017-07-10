package hibernametest.dao;

import hibernametest.model.Flights;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    static SessionFactory factory = null;

    public static void main(String[] args) {
        Tickets ticket = new Tickets();
        ticket.setClassType(1);
        ticket.setPrice(777.0);

        factory = new Configuration().configure().buildSessionFactory();
//        final Integer[] save = new Integer[1];
//        doCRUD(session -> {
//             save[0] = (Integer) session.save(ticket);
//        });
//        System.out.println(save[0]);
//        final List<Tickets>[] list = new List[1];
        doCRUD(session -> {
            List<Flights> list = session.createQuery("FROM Flights ").list();
            for (Flights flights : list) {
                System.out.println(flights + ": " + flights.getTicketsList());
            }
        });

        doCRUD(session -> {
            List<Tickets> list = session.createQuery("FROM Tickets ").list();
            for (Tickets tickets : list) {
                System.out.println(tickets );
            }
        });
////        System.out.println(list);
//        for (Tickets tickets : list[0]) {
//            System.out.println(tickets);
//        }

//        Flights flight = new Flights("WW111");
//        doCRUD(session -> {
////            Flight flight1 = session.get(Flight.class, 3);
////            session.delete(flight1);
////            session.save(flight);
//        });
//
//        doCRUD(session -> {
////            Tickets tickets = session.get(Tickets.class, 10);
////            System.out.println(tickets);
////            tickets.setPrice(999.0);
////            session.update(tickets);
////            Flight flight = session.get(Flight.class, 1);
//            Flights flights = session.get(Flights.class, 4);
//
//            Tickets t = session.get(Tickets.class, 4);
//            Tickets t = new Tickets();
//            t.setIdTicket(4);
//            t.setFlight(11);
//            t.setClassType(0);
//            t.setPrice(222.0);
//
//            t.setFlights(flights);
//
//            System.out.println(t);
//            session.saveOrUpdate(t);
//        });

//        doCRUD(session -> session.save(new Flights("PS111")));
        factory.close();
    }

    private static void doCRUD(Command command) {
        Session session = factory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            command.execute(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @FunctionalInterface
    public interface Command {
        void execute(Session session);
    }
}


