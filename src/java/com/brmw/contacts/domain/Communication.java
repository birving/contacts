/**
 * First Javadoc comment.
 */
package com.brmw.contacts.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce Irving
 *         <p/>
 *         This class represents an instance of communication between two or
 *         more people.
 *         <p/>
 */
public class Communication extends AbstractAuditedBean {
    private Date time;
    private Medium medium;
    private Person initiator;
    private Set<Person> participants = new HashSet<Person>();
    private String content;
    private String notes;

    public Date getTime() {
        return time;
    }

    public void setTime(Date date) {
        this.time = date;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Person getInitiator() {
        return initiator;
    }

    public void setInitiator(Person initiator) {
        this.initiator = initiator;
    }

    public Set<Person> getParticipants() {
        return participants;
    }

    void setParticipants(Set<Person> participants) {
        this.participants = participants;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        buf.append("Communication by ").append(medium.getType()).append(" on ").append(time);
        return buf.toString();
    }
    
}
