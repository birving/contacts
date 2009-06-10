package com.brmw.contacts.model.impl;

import java.util.Collection;

import org.hibernate.Session;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.model.AssociationTypeMaintModel;

public class AssociationTypeMaintModelImpl extends Object implements AssociationTypeMaintModel {

    @Override
    public Collection<AssociationType> getAllAssociationTypes() {
        Session session = HibernateFactory.openSession();

        @SuppressWarnings("unchecked")
        Collection<AssociationType> tableData = session.createQuery("from AssociationType").list();
        if (ContactsState.isDebugMode()) {
            // This is a bit of a hack to populate the audit data which is
            // otherwise proxied.
            // TODO: find a better way.
            for (AssociationType associationType : tableData) {
                if (associationType.getCreated() != null) {
                    associationType.getCreated().getTransactionDate();
                }
                if (associationType.getUpdated() != null) {
                    associationType.getUpdated().getTransactionDate();
                }
            }
        }
        session.close();

        return tableData;
    }
}
