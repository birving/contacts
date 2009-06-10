package com.brmw.contacts.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.EventSource;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.def.DefaultSaveOrUpdateEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Audit;
import com.brmw.contacts.domain.Auditable;

public class SaveUpdateListener extends DefaultSaveOrUpdateEventListener {
    private static final Logger logger = LoggerFactory.getLogger(SaveUpdateListener.class);

    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
        logger.debug("Executing onSaveOrUpdate()");

        if (event.getEntity() instanceof Auditable) {
            EventSource session = event.getSession();
            Audit audit = HibernateFactory.getAudit(session);
            if (audit != null && audit.getId() == null) {
                logger.debug("Executing onPreInsert() - Saving Audit!!");
                audit.setTransactionDate(new Date());
                session.save(audit);
            }
            // ((Auditable)event.getEntity()).setCreated(audit);
        }

        super.onSaveOrUpdate(event);
    }
}
