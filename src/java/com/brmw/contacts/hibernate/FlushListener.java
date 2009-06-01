package com.brmw.contacts.hibernate;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.EventSource;
import org.hibernate.event.FlushEvent;
import org.hibernate.event.def.DefaultFlushEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Audit;

public class FlushListener extends DefaultFlushEventListener {
    private static final Logger logger = LoggerFactory.getLogger(FlushListener.class);

    @Override
    public void onFlush(FlushEvent event) throws HibernateException {
        logger.debug("Executing onFlush()");
        EventSource session = event.getSession();

        Audit audit = HibernateFactory.getAudit(session);
        if (audit != null && audit.getId() == null) {
            logger.debug("Executing onFlush() - Saving Audit!!");
            audit.setTransactionDate(new Date());
            session.save(audit);
        }

        // Fall through to default listener
        super.onFlush(event);
    }
}
