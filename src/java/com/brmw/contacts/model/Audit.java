package com.brmw.contacts.model;

import java.util.Date;

/**
 * @author Bruce Irving
 * 
 *         Each database transaction is time stamped here; and each effected
 *         entity is linked here for creates and updates.
 */
public class Audit extends AbstractBean {
    private Date transactionDate;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
