/**
 * 
 */
package com.brmw.contacts.model;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 * 
 *         This class represents a company with an employment opening or a
 *         recruiter.
 */
public class Company extends AbstractAuditedBean {
    private String name; // Natural key
    private String webpage;
    private String notes;
    private Set<Position> positions = new HashSet<Position>();
    private Set<Person> contacts = new HashSet<Person>();
    private Set<Association> associations = new HashSet<Association>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpageUrl(URL webpageUrl) {
        this.webpage = webpageUrl.toString();
    }

    public void setWebpage(String webpage) {
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

    /* package access - only used by Hibernate. */
    void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Person> getContacts() {
        return contacts;
    }

    /* package access - only used by Hibernate. */
    void setContacts(Set<Person> contacts) {
        this.contacts = contacts;
    }
    
    /* Convenience method */
    public void addContact(Person contact) {
        contact.setCompany(this);
    }

    public Set<Association> getAssociations() {
        return associations;
    }

    /* package access - only used by Hibernate. */
    void setAssociations(Set<Association> associations) {
        this.associations = associations;
    }
    
    public void addAssociation(Association association) {
        associations.add(association);
    }

    @Override
    public int hashCode() {
        return (name == null) ? 0 : name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
