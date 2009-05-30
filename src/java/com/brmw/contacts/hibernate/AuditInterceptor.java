package com.brmw.contacts.hibernate;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Audit;
import com.brmw.contacts.domain.Auditable;

public class AuditInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = -5071434881441059454L;

    private static Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);
    private Audit audit;

    public AuditInterceptor(Audit audit) {
        this.audit = audit;
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onFlushDirty() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            boolean modified = false;
            // updated += 1;
            audit.setUpdated(audit.getUpdated() + 1);
            for (int i = 0; i < propertyNames.length; i++) {
                if ("updated".equals(propertyNames[i])) {
                    currentState[i] = audit;
                    modified = true;
                }
            }
            return modified;
        }
        return false;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onSave() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            // created += 1;
            audit.setCreated(audit.getCreated() + 1);
            for (int i = 0; i < propertyNames.length; i++) {
                if ("created".equals(propertyNames[i])) {
                    state[i] = audit;
                    return true;
                }
            }
        } 
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.info("Interceptor: onDelete() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            // deleted += 1;
            audit.setDeleted(audit.getDeleted() + 1);
        }
    }
}
