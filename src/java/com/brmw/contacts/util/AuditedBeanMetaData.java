package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.domain.AbstractAuditedBean;

public abstract class AuditedBeanMetaData<T extends AbstractAuditedBean> implements TableMetaData<T> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AuditedBeanMetaData.class);

    private List<FieldData<T>> columnData;

    protected AuditedBeanMetaData() {
        columnData = new ArrayList<FieldData<T>>();
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "id"));
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "version"));
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "created.transactionDate"));
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "created.id"));
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "updated.transactionDate"));
        columnData.add(new AbstractFieldData<T>(AbstractAuditedBean.class, "updated.id"));
    }

    public List<FieldData<T>> getColumnData() {
        return columnData;
    }

    public void setColumnData(List<FieldData<T>> columnData) {
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
        return getColumnData().get(columnIndex).getDisplayName();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getColumnData().get(columnIndex).getFieldClass();
    }

    @Override
    public boolean isCellEditable(int columnIndex) {
        return getColumnData().get(columnIndex).isFieldEditable();
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
