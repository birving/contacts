/**
 * 
 */
package com.brmw.contacts.model;

import java.net.URL;
import java.util.Set;

/**
 * @author Bruce Irving
 * 
 *         This class represents a company with an employment opening or a
 *         recruiter.
 */
public class Company extends AbstractAuditedBean {
    private String name;
    private URL webpage;
    private String notes;
    private Set<Position> positions;
    private Set<Person> contacts;
    private Set<CompanyAssociation> associations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getWebpage() {
        return webpage;
    }

    public void setWebpage(URL webpage) {
        this.webpage = webpage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Person> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Person> contacts) {
        this.contacts = contacts;
    }
}
