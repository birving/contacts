package com.brmw.contacts.util;

/**
 * Parameters for display of a data field in the GUI.
 * @author bruce
 *
 * @param <T> Parent Class of field
 */
public interface ColumnData<T> {
    
    public String getColumnName();
    
    public Class<?> getColumnClass();
    
    public Object getValue(T rowObject);
    
    public void setValue(T rowObject, Object value);
}
