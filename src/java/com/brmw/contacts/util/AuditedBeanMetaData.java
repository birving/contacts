package com.brmw.contacts.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.AbstractAuditedBean;

public abstract class AuditedBeanMetaData<T extends AbstractAuditedBean> implements TableMetaData<T> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AuditedBeanMetaData.class);

    private final ColumnData<T> ID_COLUMN = new AbstractColumnData<T>("Id", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getId();
        }
    };

    private final ColumnData<T> VERSION_COLUMN = new AbstractColumnData<T>("Version", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getVersion();
        }
    };

    private final ColumnData<T> CREATED_ID_COLUMN = new AbstractColumnData<T>("C-Id", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getCreated() == null ? null : audited.getCreated().getId();
        }
    };

    private final ColumnData<T> CREATED_COLUMN = new AbstractColumnData<T>("Created date", Date.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getCreated() == null ? null : audited.getCreated().getTransactionDate();
        }
    };

    private final ColumnData<T> UPDATED_ID_COLUMN = new AbstractColumnData<T>("U-Id", Long.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getUpdated() == null ? null : audited.getUpdated().getId();
        }
    };

    private final ColumnData<T> UPDATED_COLUMN = new AbstractColumnData<T>("Updated date", Date.class) {
        @Override
        public Object getValue(T audited) {
            return audited.getUpdated() == null ? null : audited.getUpdated().getTransactionDate();
        }
    };

    @SuppressWarnings("unchecked")
    private List<ColumnData<T>> columnData = Arrays.asList(ID_COLUMN, VERSION_COLUMN, CREATED_ID_COLUMN, CREATED_COLUMN, UPDATED_ID_COLUMN, UPDATED_COLUMN);

    public List<ColumnData<T>> getColumnData() {
        return columnData;
    }

    public void setColumnData(List<ColumnData<T>> columnData) {
        this.columnData = columnData;
    }

    public Boolean getIncludeDebugInfo() {
        return ContactsState.isDebugMode();
    }

    @Override
    public int getColumnCount() {
        return getColumnData().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return getColumnData().get(columnIndex).getColumnName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getColumnData().get(columnIndex).getColumnClass();
    }

    @Override
    public boolean isCellEditable(int columnIndex) {
        return getColumnData().get(columnIndex).isCellEditable();
    }

    @Override
    public Object getValueAt(int columnIndex, T audited) {
        return getColumnData().get(columnIndex).getValue(audited);
    }

    @Override
    public void setValueAt(Object value, T rowData, int columnIndex) {
        getColumnData().get(columnIndex).setValue(rowData, value);
    }
}
