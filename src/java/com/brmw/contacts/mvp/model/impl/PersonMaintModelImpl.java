package com.brmw.contacts.mvp.model.impl;

import org.hibernate.Session;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.mvp.model.PersonMaintModel;

public class PersonMaintModelImpl implements PersonMaintModel {

    @Override
    public Person getPerson(Long id) {
        Session session = HibernateFactory.openSession();
        return (Person) session.load(Person.class, id);
    }
}
