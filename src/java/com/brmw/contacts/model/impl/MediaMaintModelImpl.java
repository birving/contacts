package com.brmw.contacts.model.impl;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.model.MediaMaintModel;

public class MediaMaintModelImpl implements MediaMaintModel {

    @Override
    public Collection<Medium> getAllMedia() {
        Session session = null;
        Transaction tx = null;
        
        session = HibernateFactory.openSession();
        tx = session.beginTransaction();
        
        @SuppressWarnings("unchecked")
        Collection<Medium> tableData = session.createQuery("from Medium").list();
//        session.close();

        return tableData;
    }
}
