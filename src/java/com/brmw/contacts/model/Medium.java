/**
 * 
 */
package com.brmw.contacts.model;

/**
 * @author Bruce Irving
 * 
 *         This class represents a medium of communication (e.g. telephone,
 *         email, etc).
 * 
 */
public class Medium extends AbstractAuditedBean {
    private String name;
    private String qualifier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
