package com.brmw.contacts.swing;

import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.util.TableMetaData;

public class CollectionTableModel<T> extends AbstractTableModel {
    private static final long serialVersionUID = 6619151274626712016L;
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(CollectionTableModel.class);

    private TableMetaData<T> tableMetaData;
    private Collection<T> tableData;
    private transient T[] rows;

    public CollectionTableModel(Collection<T> tableData, TableMetaData<T> tableMetaData) {
        this.tableData = tableData;
        this.tableMetaData = tableMetaData;
    }

    @Override
    public int getColumnCount() {
        return tableMetaData.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {
        return tableMetaData.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        return tableData == null ? 0 : tableData.size();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        T rowData = getRows()[rowIndex];
        return tableMetaData.getValueAt(columnIndex, rowData);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        rows = null;
        T rowData = getRows()[rowIndex];
        tableMetaData.setValueAt(value, rowData, columnIndex);
    }


    @SuppressWarnings("unchecked")
    private T[] getRows() {
        if (rows == null) {
            rows = (T[]) tableData.toArray(); 
        }
        return rows;
    }
}
