package com.brmw.contacts.domain;

import java.io.Serializable;

/**
 * 
 * @author Bruce Irving
 * 
 *         Base class for persisted beans.
 */
public abstract class AbstractBean implements Serializable, Deleteable {

    private static final long serialVersionUID = -5626390218300805844L;
    private Long id;

    /*
     * The 'deleted' field will not be persisted to the database. Instead it
     * will flag the object for removal from the database.
     */
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}