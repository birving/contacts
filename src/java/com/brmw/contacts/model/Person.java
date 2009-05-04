/**
 * 
 */
package com.brmw.contacts.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 *         <p/>
 *         This class represents a person with whom there may be contact.
 */
public class Person extends AbstractAuditedBean {
    private String lastName;     // Natural identifier
    private String firstName;    // Natural identifier
    private Company company;
    private String role;
    private Set<Locator> locators = new HashSet<Locator>();
    private Set<Communication> communications = new HashSet<Communication>();
    private String notes;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }
}
