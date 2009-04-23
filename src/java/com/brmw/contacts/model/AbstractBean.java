package com.brmw.contacts.model;

/**
 * 
 * @author Bruce Irving
 * 
 *         Base class for persisted beans.
 */
public abstract class AbstractBean {

    private Long ident;

    public AbstractBean() {
        super();
    }

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

}