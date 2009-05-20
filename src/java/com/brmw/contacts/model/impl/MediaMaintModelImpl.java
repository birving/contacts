package com.brmw.contacts.model.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.model.MediaMaintModel;

public class MediaMaintModelImpl implements MediaMaintModel {

    @Override
    public Collection<Medium> getAllMedia() {
        
        Session session = HibernateFactory.openSession();
        
        @SuppressWarnings("unchecked")
        Collection<Medium> tableData = session.createQuery("select from Medium").list();
        
//        // Add some test data ...
//        Collection<Medium> tableData = new ArrayList<Medium>();
//        Medium medium1 = new Medium();
//        medium1.setName("Primary email");
//        medium1.setType("email");
//        tableData.add(medium1);
//
//        Medium medium2 = new Medium();
//        medium2.setName("Another email");
//        medium2.setType("email");
//        tableData.add(medium2);
//
//        Medium medium3 = new Medium();
//        medium3.setName("Home phone");
//        medium3.setType("phone");
//        tableData.add(medium3);

        return tableData;
    }

}
