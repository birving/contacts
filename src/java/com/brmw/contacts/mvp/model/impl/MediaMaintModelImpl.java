package com.brmw.contacts.mvp.model.impl;

import java.util.Collection;

import org.hibernate.Session;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.mvp.model.MediaMaintModel;

public class MediaMaintModelImpl implements MediaMaintModel {

    @Override
    public Collection<Medium> getAllMedia() {
        Session session = HibernateFactory.openSession();

        @SuppressWarnings("unchecked")
        Collection<Medium> tableData = session.createQuery("from Medium").list();
        if (ContactsState.isDebugMode()) {
            // This is a bit of a hack to populate the audit data which is
            // otherwise proxied.
            // TODO: find a better way.
            for (Medium medium : tableData) {
                if (medium.getCreated() != null) {
                    medium.getCreated().getTransactionDate();
                }
                if (medium.getUpdated() != null) {
                    medium.getUpdated().getTransactionDate();
                }
            }
        }
        session.close();

        return tableData;
    }
}
