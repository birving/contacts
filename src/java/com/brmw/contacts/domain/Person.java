/**
 * 
 */
package com.brmw.contacts.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 *         <p/>
 *         This class represents a person with whom there may be contact.
 */
public class Person extends AbstractAuditedBean {
    private String uniqueName;    // Must be unique; will be used for sort
    private String displayName; // As the app should display it.
    private Company company;
    private String role;
    private Set<Locator> locators = new HashSet<Locator>();
    private Set<Communication> communications = new HashSet<Communication>();
    private String notes;

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company newCompany) {
        if (company != null) {
            company.getContacts().remove(this);
        }
        company = newCompany;
        newCompany.getContacts().add(this);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Locator> getLocators() {
        return locators;
    }

    /* package access - only used by Hibernate. */
    void setLocators(Set<Locator> locators) {
        this.locators = locators;
    }

    public void addLocator(Locator locator) {
        locator.setPerson(this);
        locators.add(locator);
    }

    public Set<Communication> getCommunications() {
        return communications;
    }

    /* package access - only used by Hibernate. */
    void setCommunications(Set<Communication> communications) {
        this.communications = communications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (displayName != null) {
            buf.append(displayName).append(" ");
        }
        if (uniqueName != null) {
            buf.append(uniqueName);
        }
        return buf.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + ((uniqueName == null) ? 0 : uniqueName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (displayName == null) {
            if (other.displayName != null)
                return false;
        } else if (!displayName.equals(other.displayName))
            return false;
        if (uniqueName == null) {
            if (other.uniqueName != null)
                return false;
        } else if (!uniqueName.equals(other.uniqueName))
            return false;
        return true;
    }
}
