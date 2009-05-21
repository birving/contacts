package com.brmw.contacts.util;

public abstract class AbstractColumnData<T> implements ColumnData<T> {

    private String columnName;
    private Class<?> columnclass;
    private Boolean cellEditable;
    
    protected AbstractColumnData(String columnName) {
        this.columnName = columnName;
        this.columnclass = String.class;
        this.cellEditable = Boolean.FALSE;
    }
    
    protected AbstractColumnData(String columnName, Boolean cellEditable) {
        this.columnName = columnName;
        this.columnclass = String.class;
        this.cellEditable = cellEditable;
    }

    protected AbstractColumnData(String columnName, Class<?> columnclass) {
        this.columnName = columnName;
        this.columnclass = columnclass;
        this.cellEditable = Boolean.FALSE;
    }

    protected AbstractColumnData(String columnName, Class<?> columnclass, Boolean cellEditable) {
        this.columnName = columnName;
        this.columnclass = columnclass;
        this.cellEditable = cellEditable;
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
    public boolean isCellEditable() {
        return this.cellEditable;
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
