package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         Base class for audited model beans.
 */
public abstract class AbstractAuditedBean extends AbstractBean {

    private Audit created;
    private Audit updated;

    public Audit getCreated() {
        return created;
    }

    public void setCreated(Audit audit) {
        this.created = audit;
    }

    public Audit getUpdated() {
        return updated;
    }

    public void setUpdated(Audit updated) {
        this.updated = updated;
    }

}