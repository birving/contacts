/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         Companies may be associated as parent/subsidiary, employer/recruiter,
 *         etc.
 */
public class CompanyAssociation extends AbstractAuditedBean {
    private Company company1;
    private Company company2;
    private AssociationType associationType;
    private String notes;

    public Company getCompany1() {
        return company1;
    }

    public void setCompany1(Company company1) {
        this.company1 = company1;
    }

    public Company getCompany2() {
        return company2;
    }

    public void setCompany2(Company company2) {
        this.company2 = company2;
    }

    public AssociationType getAssociationType() {
        return associationType;
    }

    public void setAssociationType(AssociationType associationType) {
        this.associationType = associationType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
