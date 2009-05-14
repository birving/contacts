/**
 * 
 */
package com.brmw.contacts.domain;

/**
 * @author Bruce Irving
 * 
 *         This class represents an employments opportunity at a specific
 *         company.
 */
public class Position extends AbstractAuditedBean {
    private Company employer;
    private String identifier;
    private String description;
    private String status;
    private Company recruiterCompany;
    private String notes;

    public Company getEmployer() {
        return employer;
    }

    public void setEmployer(Company employer) {
        this.employer = employer;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Company getRecruiterCompany() {
        return recruiterCompany;
    }

    public void setRecruiterCompany(Company recruiterCompany) {
        this.recruiterCompany = recruiterCompany;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
