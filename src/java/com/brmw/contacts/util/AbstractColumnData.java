package com.brmw.contacts.util;

public abstract class AbstractColumnData<T> implements ColumnData<T> {

    private String columnName;
    private Class<?> columnclass;
    
    protected AbstractColumnData(String columnName) {
        this.columnName = columnName;
        this.columnclass = String.class;
    }
    
    protected AbstractColumnData(String columnName, Class<?> columnclass) {
        this.columnName = columnName;
        this.columnclass = columnclass;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public Class<?> getColumnClass() {
        return columnclass;
    }

    @Override
    abstract public Object getValue(T rowObject);

    @Override
    /**
     * Default to read-only column; Override if should be write-able.
     */
    public void setValue(T rowObject, Object value) {
        throw new UnsupportedOperationException();
    }

}
