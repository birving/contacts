package com.brmw.contacts.domain;

import java.util.Date;


/**
 * @author Bruce Irving
 * 
 *         Each database transaction is time stamped here; and each effected
 *         entity is linked here for creates and updates.
 */
public class Audit extends AbstractBean {
    private Date transactionDate;
    private Integer created = 0;
    private Integer updated = 0;
    private Integer deleted = 0;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getUpdated() {
        return updated;
    }

    public void setUpdated(Integer updated) {
        this.updated = updated;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
