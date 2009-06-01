package com.brmw.contacts.util;

/**
 * Represents column headings and data types for a table of type <T>
 * 
 * @param T
 *            Type of data in the table.
 */
public interface TableMetaData<T> {

    public int getColumnCount();

    public String getColumnName(int columnIndex);

    public Class<?> getColumnClass(int columnIndex);

    public boolean isCellEditable(int columnIndex);

    public Object getValueAt(int columnIndex, T rowObject);

    public void setValueAt(Object value, T rowData, int columnIndex);

    /**
     * This factory method is needed since it is not possible to create an
     * object of a parameterized type. In other words, if it were possible we
     * would rather do this: <code>new T()</code>
     * <p/>
     * For a good explanation, see <a href="http://www.angelikalanger.com/GenericsFAQ/FAQSections/TypeParameters.html#FAQ201"
     * >this page</a>.
     * 
     * @return A new instance if the specified type
     */
    public T createInstance();
}
