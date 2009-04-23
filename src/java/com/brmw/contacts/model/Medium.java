/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         This class represents a medium of communication (e.g. telephone,
 *         email, etc).
 * 
 */
public class Medium extends AbstractAuditedBean {
    private String type;
    private String name;
    private String notes;

    public String getType() {
        return type;
    }

    public void setType(String types) {
        this.type = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
