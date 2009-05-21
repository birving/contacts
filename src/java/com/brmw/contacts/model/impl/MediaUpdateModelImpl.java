package com.brmw.contacts.model.impl;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.model.MediaUpdateModel;
import com.brmw.contacts.test.TestDataLoader;

public class MediaUpdateModelImpl implements MediaUpdateModel {
    private static Logger logger = LoggerFactory.getLogger(MediaUpdateModelImpl.class);

    @Override
    public Collection<Medium> updateAllMedia(Collection<Medium> media) {
        Session session = null;
        Transaction tx = null;
        
        try {
            session = HibernateFactory.openSession();
            tx = session.beginTransaction();

            for (Medium medium : media) {
                session.saveOrUpdate(medium);
            }
            session.flush();
            tx.commit();
            logger.info("Media updates committed.");
        } catch (HibernateException e) {
            logger.info("Unable to commit medium 1&2.");
            HibernateFactory.rollback(tx);
            throw e;
        } finally {
            HibernateFactory.close(session);
       }
        
        
//        session = HibernateFactory.openSession();
//        @SuppressWarnings("unchecked")
//        Collection<Medium> tableData = session.createQuery("from Medium").list();
//        session.close();
        return media;
    }
}
