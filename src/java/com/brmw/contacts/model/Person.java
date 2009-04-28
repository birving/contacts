/**
 * 
 */
package com.brmw.contacts.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 * 
 *         This class represents a person with whom there may be contact.
 */
public class Person extends AbstractAuditedBean {
    private String lastName;
    private String firstName;
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

    public void setCompany(Company company) {
        this.company = company;
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

    void setCommunications(Set<Communication> communications) {
        this.communications = communications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
