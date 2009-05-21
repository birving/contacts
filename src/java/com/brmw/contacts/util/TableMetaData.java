package com.brmw.contacts.util;

/**
 * Represents column headings and data types for a table of type <T>
 * 
 * @param T Type of data in the table.
 */
public interface TableMetaData<T> {

    public int getColumnCount();

    public String getColumnName(int columnIndex);

    public Class<?> getColumnClass(int columnIndex);
    
    public boolean isCellEditable(int columnIndex);

    public Object getValueAt(int columnIndex, T rowObject);

    public void setValueAt(Object value, T rowData, int columnIndex);
}
