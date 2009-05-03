/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 *         <p/>
 *         This class represents a specific means of communication (e.g. phone
 *         number, email address, etc).
 * 
 *         This class is a child of the Person class, not an independent entity.
 * 
 */
public class Locator extends AbstractAuditedBean {
    private Person person;
    private Medium medium;
    private String value;    // Natural key
    private String notes;

    public Person getPerson() {
        return person;
    }

    /*
     * This uses package visibility since it should only be set via
     * Person.addLocator() or by Hibernate.
     */
    void setPerson(Person person) {
        this.person = person;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        return (value == null) ? 0 : value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Locator other = (Locator) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }
}
