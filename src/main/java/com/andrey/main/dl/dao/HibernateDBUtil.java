package com.andrey.main.dl.dao;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class HibernateDBUtil {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();
    //    private static SessionFactory factory;
    private static final Logger log = Logger.getLogger(HibernateDBUtil.class);

    public static void setFactory(SessionFactory factory) {
        HibernateDBUtil.factory = factory;
    }


    public static void operationCRUD(Command command) {
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
            log.error(e);

        } finally {
            session.close();
        }
    }

    public static void shutdownConnection() {
        factory.close();
    }

    @FunctionalInterface
    public interface Command {
        void execute(Session session);
    }
}
