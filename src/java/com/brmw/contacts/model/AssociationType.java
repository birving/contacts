/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         An AssociationType describes the nature of the association between
 *         two companies.
 * 
 *         Design Note: This may also be used for the association between people
 *         or other entitles.
 */
public class AssociationType extends AbstractAuditedBean {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
