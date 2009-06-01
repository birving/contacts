package com.brmw.contacts.hibernate;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
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
        logger.debug("Interceptor: onFlushDirty() - entity {}", entity.getClass().getName());
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
        logger.debug("Interceptor: onSave() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            // created += 1;
            audit.setCreated(audit.getCreated() + 1);
            for (int i = 0; i < propertyNames.length; i++) {
                if ("created".equals(propertyNames[i])) {
                    state[i] = audit;
                    // return true;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.debug("Interceptor: onDelete() - entity {}", entity.getClass().getName());
        if (entity instanceof Auditable) {
            // deleted += 1;
            audit.setDeleted(audit.getDeleted() + 1);
        }
    }

    // Additional overrides for debugging only
   
    @Override
    public void afterTransactionBegin(Transaction tx) {
        logger.debug("Interceptor: afterTransactionBegin()");
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        logger.debug("Interceptor: afterTransactionCompletion()");
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        logger.debug("Interceptor: beforeTransactionCompletion()");
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        logger.debug("Interceptor: findDirty() - entity {}", entity.getClass().getName());
        return super.findDirty(entity, id, currentState, previousState, propertyNames, types);
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        logger.debug("Interceptor: getEntity() - entityname={}", entityName);
        return super.getEntity(entityName, id);
    }

    @Override
    public String getEntityName(Object object) {
        logger.debug("Interceptor: getEntityName() - object {}", object.getClass().getName());
        return super.getEntityName(object);
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        logger.debug("Interceptor: instantiate() - entityName={}", entityName);
        return super.instantiate(entityName, entityMode, id);
    }

    @Override
    public Boolean isTransient(Object entity) {
        logger.debug("Interceptor: isTransient() - entity {}", entity.getClass().getName());
        return super.isTransient(entity);
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        logger.debug("Interceptor: onCollectionRecreate()");
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        logger.debug("Interceptor: onCollectionRemove()");
        super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        logger.debug("Interceptor: onCollectionUpdate()");
        super.onCollectionUpdate(collection, key);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        logger.debug("Interceptor: onLoad()");
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public String onPrepareStatement(String sql) {
        logger.debug("Interceptor: onPrepareStatement()");
        return super.onPrepareStatement(sql);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void postFlush(Iterator entities) {
        logger.debug("Interceptor: postFlush()");
        super.postFlush(entities);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void preFlush(Iterator entities) {
        logger.debug("Interceptor: preFlush()");
        super.preFlush(entities);
    }
}
