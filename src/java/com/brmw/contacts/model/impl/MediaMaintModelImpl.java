package com.brmw.contacts.model.impl;

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
        Collection<Medium> tableData = session.createQuery("from Medium").list();
        session.close();

        return tableData;
    }

//    @Override
//    public Collection<Medium> updateAllMedia(Collection<Medium> media) {
//        // TODO Auto-generated method stub
//        return null;
//    }
}
