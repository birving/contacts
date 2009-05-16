package com.brmw.contacts.util;

/**
 * Represents column headings and data types for a table of type <T>
 * 
 * @param T Type of data in the table.
 */
public interface TableMetaData<T> {

    public int getColumnCount();

    public String getColumnName(int col);

    public Class<?> getColumnClass(int col);

    public Object getValueAt(int col, T rowObject);

    public void setValueAt(Object value, T rowData, int columnIndex);
}
