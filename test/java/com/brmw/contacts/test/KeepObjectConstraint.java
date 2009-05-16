package com.brmw.contacts.test;

/**
 * This class is used to evaluate an object through the jmock framework, and
 * keep reference to that object.
 * 
 * This class is very handy when a test wants to simulate an event.
 */
public class KeepObjectConstraint // implements Constraint
{
    /** the evaluation object */
    private Object m_evalObject;

    public boolean eval(Object object) {
        m_evalObject = object;

        // Always evaluate to true
        return true;
    }

    public StringBuffer describeTo(StringBuffer stringBuffer) {
        return stringBuffer;
    }

    /**
     * Gets the evaluation object. This allows an event to be fired.
     * 
     * @return the evaluation object.
     */
    public Object getEvalObject() {
        return m_evalObject;
    }
}
