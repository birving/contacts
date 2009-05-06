package com.brmw.contacts.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.Audit;

public class HibernateFactory {
    private static Logger logger = LoggerFactory.getLogger(HibernateFactory.class);
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
        Audit audit = new Audit();
        Session session = sessionFactory.openSession(new AuditInterceptor(audit));
        session.save(audit);
        return session;
    }

    public static void closeFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException ignored) {
                logger.warn("Unable to close Hibernate SessionFactory", ignored);
            }

        }
    }
    
    public static void close(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
                logger.warn("Unable to close Hibernate session", ignored);
            }
        }
    }

    public static void rollback(Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
            } catch (HibernateException ignored) {
                logger.warn("Unable to rollback Hibernate transaction", ignored);
            }
        }
    }
}
