/**
 * 
 */
package com.brmw.contacts.model;

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
    private Set<Locator> locators;
    private Set<Communication> communications;

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

    public Set<Locator> getLocators() {
        return locators;
    }

    public void setLocators(Set<Locator> locators) {
        this.locators = locators;
    }

    public Set<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(Set<Communication> communications) {
        this.communications = communications;
    }

}
