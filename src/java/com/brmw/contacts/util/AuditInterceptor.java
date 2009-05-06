package com.brmw.contacts.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.Audit;
import com.brmw.contacts.model.Auditable;

public class AuditInterceptor extends EmptyInterceptor {
    private static Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);
    private Audit audit;
    private Integer created = 0;
    private Integer updated = 0;
    private Integer deleted = 0;
    
    public AuditInterceptor(Audit audit) {
        this.audit = audit;
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        logger.info("Interceptor: beforeTransactionCompletion()" );
    }

    
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onFlushDirty() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            updated++;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updated".equals(propertyNames[i])) {
                    currentState[i] = audit;
                    return true;
                }
            }
        }
        return false;
   }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onSave() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            created++;
            for (int i = 0; i < propertyNames.length; i++) {
                if ("created".equals(propertyNames[i])) {
                    state[i] = audit;
                    return true;
                }
            }
        }
        if (entity instanceof Audit) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("transactionDate".equals(propertyNames[i])) {
                    state[i] = new Date();
                }
                if ("created".equals(propertyNames[i])) {
                    state[i] = created;
                }
                if ("updated".equals(propertyNames[i])) {
                    state[i] = updated;
                }
                if ("deleted".equals(propertyNames[i])) {
                    state[i] = deleted;
                }
            }
        }
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onDelete() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            deleted++;
        }
    }

    @Override
    public void preFlush(Iterator entities) {
        logger.info("Interceptor: onPreFlush()" );
        while (entities.hasNext()) {
            Object entity = entities.next();
            logger.debug("Entity {}", entity.getClass().getName());
            if (entity instanceof Audit) {
                Audit audit = (Audit) entity;
                audit.setCreated(created);
                audit.setUpdated(updated);
                audit.setDeleted(deleted);
            }
        }        
    }

}
