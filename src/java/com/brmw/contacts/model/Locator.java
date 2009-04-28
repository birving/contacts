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
    private String value;
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
}
