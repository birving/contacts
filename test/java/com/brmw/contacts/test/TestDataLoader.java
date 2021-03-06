package com.brmw.contacts.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;

/**
 * Simple class to stuff some test data into the database.
 */
public class TestDataLoader {
    private static Logger logger = LoggerFactory.getLogger(TestDataLoader.class);

    public static void main(String[] args) {
        // Initial Hibernate startup.
        HibernateFactory.buildSessionFactory();

        Medium medium1 = new Medium();
        medium1.setName("Email 6");
        medium1.setType("email");
        Medium medium2 = new Medium();
        medium2.setName("Email 7");
        medium2.setType("email");

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateFactory.openSession();
            Query mediumQuery = session.createQuery("from Medium");
            @SuppressWarnings("unchecked")
            List<Medium> media = mediumQuery.list();
            tx = session.beginTransaction();
            if (media.contains(medium1)) {
                medium1 = media.get(media.indexOf(medium1));
            } else {
                session.save(medium1);
            }
            if (media.contains(medium2)) {
                medium2 = media.get(media.indexOf(medium2));
            } else {
                session.save(medium2);
            }
            session.flush();
            tx.commit();
            logger.info("Medium 1&2 committed.");
        } catch (HibernateException e1) {
            logger.info("Unable to commit medium 1&2.");
            HibernateFactory.rollback(tx);
            throw e1;
        } finally {
            HibernateFactory.close(session);
        }

/*
        Company company = new Company();
        company.setName("Acme");
        try {
            company.setWebpageUrl(new URL("http://www.acme.com"));
        } catch (MalformedURLException e) {
            company.setWebpage("Invalid URL.");
        }

        Person person1 = new Person();
        person1.setDisplayName("Jim Smith");
        person1.setUniqueName("Smith, Jim");

        Locator locator1 = new Locator();
        locator1.setMedium(medium1);
        locator1.setValue("joe.smith@acme.com");
        person1.addLocator(locator1);

        Locator locator2 = new Locator();
        locator2.setMedium(medium2);
        locator2.setValue("joeSmith892@yahoo.com");
        person1.addLocator(locator2);

        person1.setCompany(company);

        try {
            session = HibernateFactory.openSession();
            tx = session.beginTransaction();
            session.save(person1);
            session.save(company);
            session.flush();
            tx.commit();
            logger.info("person & company comm  itted.");
        } catch (HibernateException e1) {
            logger.warn("Unable to commit person & company.");
            HibernateFactory.rollback(tx);
            throw e1;
        } finally {
            HibernateFactory.close(session);
        }
        HibernateFactory.closeFactory();
*/
    }
}
