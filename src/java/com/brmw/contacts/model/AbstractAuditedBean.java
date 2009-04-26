package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 *         <p/>
 *         Base class for audited model beans.
 */
public abstract class AbstractAuditedBean extends AbstractBean {

    private Long version;
    private Audit created;
    private Audit updated;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

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