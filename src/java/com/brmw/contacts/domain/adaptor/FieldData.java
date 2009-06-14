package com.brmw.contacts.domain.adaptor;

/**
 * Parameters for display of a data field in the GUI.
 * 
 * @author bruce
 * 
 * @param <T>
 *            Parent Class of field
 */
public interface FieldData<T> {

    public String getDisplayName();

    public Class<?> getFieldClass();

    public boolean isFieldEditable();

    public Object getValue(T rowObject);

    public void setValue(T rowObject, Object value);
}
