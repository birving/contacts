package com.brmw.contacts.hibernate;

import java.io.Serializable;

/**
 * 
 * @author Bruce Irving
 * 
 *         Base class for persisted beans.
 */
public abstract class AbstractBean implements Serializable {

    private Long id;

    public AbstractBean() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}