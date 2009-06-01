package com.brmw.contacts.hibernate;

import java.util.Date;

import org.hibernate.event.EventSource;
import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Audit;
import com.brmw.contacts.domain.Auditable;

public class PreInsertListener implements PreInsertEventListener {
    private static final Logger logger = LoggerFactory.getLogger(PreInsertListener.class);

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        logger.debug("Executing onPreInsert()");

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
        return false;
    }
}
