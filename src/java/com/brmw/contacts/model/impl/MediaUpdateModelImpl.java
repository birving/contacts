package com.brmw.contacts.model.impl;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.model.MediaUpdateModel;

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
                if (medium.isDeleted()) {
                    session.delete(medium);
                } else if (medium.getId() == null) {
                    Long id = (Long) session.save(medium);
                    medium.setId(id);
                } else {
                    medium = (Medium) session.merge(medium);
                }
            }
            session.flush();
            tx.commit();
            logger.info("Media updates committed.");
            
            for (Iterator<Medium> iter = media.iterator(); iter.hasNext(); ){
                if (iter.next().isDeleted()) {
                    iter.remove();
                }
            }
            

            // This may not do what I would like.
            for (Medium medium : media) {
                session.refresh(medium);
                if (medium.getCreated() != null) {
                    session.refresh(medium.getCreated());
                }
                if (medium.getUpdated() != null) {
                    session.refresh(medium.getUpdated());
                }
            }

        } catch (HibernateException e) {
            logger.info("Unable to commit media.");
            HibernateFactory.rollback(tx);
            throw e;
        } finally {
            HibernateFactory.close(session);
        }

        // This is ugly - I want to refresh my copy of the medium table and hope
        // to be able to that in the original session. Instead I am duplicating
        // code from MediaMaintModelImpl.

        session = HibernateFactory.openSession();
        @SuppressWarnings("unchecked")
        Collection<Medium> media2 = (Collection<Medium>) session.createQuery("from Medium").list();

        if (ContactsState.isDebugMode()) {
            // This is a bit of a hack to populate the audit data which is
            // otherwise proxied.
            // TODO: find a better way.
            for (Medium medium : media2) {
                if (medium.getCreated() != null) {
                    medium.getCreated().getTransactionDate();
                }
                if (medium.getUpdated() != null) {
                    medium.getUpdated().getTransactionDate();
                }
            }
        }
        HibernateFactory.close(session);

        return media2;
    }
}
