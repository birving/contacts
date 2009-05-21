package com.brmw.contacts.swing;

import java.util.Collection;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.util.TableMetaData;

/**
 * This class serves as an adapter from a
 * <code>java.util.Collection&lt;T&gt;</code> to a
 * <code>javax.swing.TableModel</code>. Each T object will be used to generate
 * one row of the table. The table layout parameters are taken from class
 * <code>TableMetaData&lt;T&gt;</code>
 * 
 * @author bruce
 * 
 * @param <T>
 *            Some domain object class which will be displayed as rows in a
 *            <code>javax.swing.JTable</code>.
 */
public class CollectionTableModel<T> extends AbstractTableModel {
    private static final long serialVersionUID = 6619151274626712016L;
    private static final Logger logger = LoggerFactory.getLogger(CollectionTableModel.class);

    private TableMetaData<T> tableMetaData;
    private Collection<T> tableData;
    private transient T[] rows;

    public CollectionTableModel(Collection<T> tableData, TableMetaData<T> tableMetaData) {
        this.tableData = tableData;
        this.tableMetaData = tableMetaData;
    }

    public Collection<T> getTableData() {
        return tableData;
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
        Object currentValue = this.getValueAt(rowIndex, columnIndex);
        if (currentValue == null || !currentValue.equals(value)) {
            tableMetaData.setValueAt(value, rowData, columnIndex);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @SuppressWarnings("unchecked")
    private T[] getRows() {
        if (rows == null) {
            rows = (T[]) tableData.toArray();
        }
        return rows;
    }

    // ***** Temporary overrides for testing *****
    @Override
    public int findColumn(String columnName) {
        logger.debug("calling findColumn()");
        return super.findColumn(columnName);
    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        logger.debug("calling fireTableCellUpdated()");
        super.fireTableCellUpdated(row, column);
    }

    @Override
    public void fireTableChanged(TableModelEvent e) {
        logger.debug("calling fireTableChanged()");
        super.fireTableChanged(e);
    }

    @Override
    public void fireTableDataChanged() {
        logger.debug("calling fireTableDataChanged()");
        super.fireTableDataChanged();
    }

    @Override
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        logger.debug("calling fireTableRowsDeleted()");
        super.fireTableRowsDeleted(firstRow, lastRow);
    }

    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        logger.debug("calling fireTableRowsInserted()");
        super.fireTableRowsInserted(firstRow, lastRow);
    }

    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        logger.debug("calling fireTableRowsUpdated()");
        super.fireTableRowsUpdated(firstRow, lastRow);
    }

    @Override
    public void fireTableStructureChanged() {
        logger.debug("calling fireTableStructureChanged()");
        super.fireTableStructureChanged();
    }
}
