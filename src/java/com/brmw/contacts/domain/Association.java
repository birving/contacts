/**
 * 
 */
package com.brmw.contacts.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 *         <p/>
 *         Companies may be associated as parent/subsidiary, employer/recruiter,
 *         etc. as specified by <code>AssociationType</code>
 */
public class Association extends AbstractAuditedBean {
    private Set<Company> companies = new HashSet<Company>();
    private AssociationType associationType;
    private String notes;

    public Set<Company> getCompanies() {
        return companies;
    }

    void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public void addCompany(Company company) {
        companies.add(company);
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
