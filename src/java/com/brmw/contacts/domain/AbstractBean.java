package com.brmw.contacts.domain;

import java.io.Serializable;

/**
 * 
 * @author Bruce Irving
 * 
 *         Base class for persisted beans.
 */
public abstract class AbstractBean implements Serializable {

    private static final long serialVersionUID = -5626390218300805844L;
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