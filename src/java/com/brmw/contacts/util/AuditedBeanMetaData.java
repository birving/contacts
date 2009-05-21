package com.brmw.contacts.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AbstractAuditedBean;
import com.brmw.contacts.swing.MediaMaintDisplay;

public class AuditedBeanMetaData<T extends AbstractAuditedBean> implements TableMetaData<T> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AuditedBeanMetaData.class);

//    private Boolean includeDebugInfo = true;

    private final ColumnData<T> ID_COLUMN = new AbstractColumnData<T>("Id", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getId();
        }
    };

    private final ColumnData<T> CREATED_COLUMN = new AbstractColumnData<T>("Created date", Date.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getUpdated() == null ? null : audited.getUpdated().getTransactionDate();
        }
    };

    private final ColumnData<T> UPDATED_COLUMN = new AbstractColumnData<T>("Updated date", Date.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getUpdated() == null ? null : audited.getUpdated().getTransactionDate();
        }
    };

    private final ColumnData<T> VERSION_COLUMN = new AbstractColumnData<T>("Version", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getVersion();
        }
    };

    @SuppressWarnings("unchecked")
    private List<ColumnData<T>> columnData = Arrays.asList(ID_COLUMN, CREATED_COLUMN, UPDATED_COLUMN, VERSION_COLUMN);
    
    public List<ColumnData<T>> getColumnData() {
        return columnData;
    }

    public void setColumnData(List<ColumnData<T>> columnData) {
        this.columnData = columnData;
    }

    public Boolean getIncludeDebugInfo() {
        return MediaMaintDisplay.getDebugMode();
    }

//    public void setIncludeDebugInfo(Boolean includeDebugInfo) {
//        this.includeDebugInfo = includeDebugInfo;
//    }

    @Override
    public int getColumnCount() {
        return getColumnData().size();
    }

    @Override
    public String getColumnName(int col) {
        return getColumnData().get(col).getColumnName();
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return getColumnData().get(col).getColumnClass();
    }

    @Override
    public Object getValueAt(int col, T audited) {
        return getColumnData().get(col).getValue(audited);
    }

    @Override
    public void setValueAt(Object value, T rowData, int col) {
        getColumnData().get(col).setValue(rowData, value);
    }
}
