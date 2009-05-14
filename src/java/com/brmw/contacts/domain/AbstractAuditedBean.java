package com.brmw.contacts.domain;


/**
 * @author Bruce Irving
 *         <p/>
 *         Base class for audited model beans.
 */
public abstract class AbstractAuditedBean extends AbstractBean implements Auditable {

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

    /* (non-Javadoc)
     * @see com.brmw.contacts.model.Auditable#setCreated(com.brmw.contacts.model.Audit)
     */
    public void setCreated(Audit audit) {
        this.created = audit;
    }

    public Audit getUpdated() {
        return updated;
    }

    /* (non-Javadoc)
     * @see com.brmw.contacts.model.Auditable#setUpdated(com.brmw.contacts.model.Audit)
     */
    public void setUpdated(Audit updated) {
        this.updated = updated;
    }

}