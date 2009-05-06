package com.brmw.contacts.model;

public interface Auditable {

    public abstract void setCreated(Audit audit);

    public abstract void setUpdated(Audit updated);

}