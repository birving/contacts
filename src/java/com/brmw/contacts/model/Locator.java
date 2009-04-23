/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         This class represents a specific means of communication (e.g. phone
 *         number, email address, etc).
 * 
 */
public class Locator extends AbstractAuditedBean {
    private Medium medium;
    private String value;
    private String notes;

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
