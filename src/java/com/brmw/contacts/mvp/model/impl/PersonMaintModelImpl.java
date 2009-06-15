package com.brmw.contacts.mvp.model.impl;

import java.util.Collection;

import org.hibernate.Session;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.Person;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.mvp.model.PersonMaintModel;

public class PersonMaintModelImpl implements PersonMaintModel {

    @Override
    public Collection<Person> getAllPersons() {
        Session session = HibernateFactory.openSession();

        @SuppressWarnings("unchecked")
        Collection<Person> tableData = session.createQuery("from Person").list();
        if (ContactsState.isDebugMode()) {
            // This is a bit of a hack to populate the audit data which is
            // otherwise proxied.
            // TODO: find a better way.
            for (Person person : tableData) {
                if (person.getCreated() != null) {
                    person.getCreated().getTransactionDate();
                }
                if (person.getUpdated() != null) {
                    person.getUpdated().getTransactionDate();
                }
            }
        }
        session.close();

        return tableData;
    }
}
