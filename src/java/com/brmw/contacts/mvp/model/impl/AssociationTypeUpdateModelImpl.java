package com.brmw.contacts.mvp.model.impl;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.mvp.model.AssociationTypeUpdateModel;

public class AssociationTypeUpdateModelImpl implements AssociationTypeUpdateModel {
    private static Logger logger = LoggerFactory.getLogger(AssociationTypeUpdateModelImpl.class);

    @Override
    public Collection<AssociationType> updateAllAssociationTypes(Collection<AssociationType> associationTypes) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateFactory.openSession();
            tx = session.beginTransaction();

            for (AssociationType associationType : associationTypes) {
                if (associationType.isDeleted()) {
                    session.delete(associationType);
                } else if (associationType.getId() == null) {
                    Long id = (Long) session.save(associationType);
                    associationType.setId(id);
                } else {
                    associationType = (AssociationType) session.merge(associationType);
                }
            }
            session.flush();
            tx.commit();
            logger.info("AssociationType updates committed.");

            for (Iterator<AssociationType> iter = associationTypes.iterator(); iter.hasNext();) {
                if (iter.next().isDeleted()) {
                    iter.remove();
                }
            }

            // This may not do what I would like.
            for (AssociationType associationType : associationTypes) {
                session.refresh(associationType);
                if (associationType.getCreated() != null) {
                    session.refresh(associationType.getCreated());
                }
                if (associationType.getUpdated() != null) {
                    session.refresh(associationType.getUpdated());
                }
            }

        } catch (HibernateException e) {
            logger.info("Unable to commit associationTypes.");
            HibernateFactory.rollback(tx);
            throw e;
        } finally {
            HibernateFactory.close(session);
        }

        // This is ugly - I want to refresh my copy of the associationType table
        // and hope to be able to that in the original session. Instead I am
        // duplicating code from AssociationTypeMaintModelImpl.

        session = HibernateFactory.openSession();
        @SuppressWarnings("unchecked")
        Collection<AssociationType> associationTypes2 =
                (Collection<AssociationType>) session.createQuery("from AssociationType").list();

        if (ContactsState.isDebugMode()) {
            // This is a bit of a hack to populate the audit data which is
            // otherwise proxied.
            // TODO: find a better way.
            for (AssociationType associationType : associationTypes2) {
                if (associationType.getCreated() != null) {
                    associationType.getCreated().getTransactionDate();
                }
                if (associationType.getUpdated() != null) {
                    associationType.getUpdated().getTransactionDate();
                }
            }
        }
        HibernateFactory.close(session);

        return associationTypes2;
    }
}
