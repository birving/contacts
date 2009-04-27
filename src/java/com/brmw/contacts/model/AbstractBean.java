package com.brmw.contacts.model;

/**
 * 
 * @author Bruce Irving
 * 
 *         Base class for persisted beans.
 */
public abstract class AbstractBean {

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