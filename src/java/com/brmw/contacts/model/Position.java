/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         This class represents an employments opportunity at a specific
 *         company.
 */
public class Position extends AbstractAuditedBean {
    private String name;
    private String notes;
    private Company employer;

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

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }
}
