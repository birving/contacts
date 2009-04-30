package com.brmw.contacts.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
    private static Logger logger = Logger.getLogger(HibernateFactory.class.getName());
    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory() throws HibernateException {
        if (sessionFactory != null) {
            closeFactory();
        }
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();

        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {
                logger.log(Level.WARNING, "Unable to close Hibernate SessionFactory", ignored);
            }

        }
    }
    
    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                logger.log(Level.WARNING, "Unable to close Hibernate session", ignored);
            }
        }
    }

    public static void rollback(Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
            } catch (HibernateException ignored) {
                logger.log(Level.WARNING, "Unable to rollback Hibernate transaction", ignored);
            }
        }
    }
}
