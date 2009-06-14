package com.brmw.contacts.domain.adaptor;

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
    private String registryKey;
    // private String tableHeader;
    private boolean deleteable;

    /**
     * 
     * @param registryKey
     *            Key used to identify this component to the ComponentRegistry.
     *            Also used as a prefix for the ResourceFactory.
     * @param deleteable
     *            Can instances of the underlying data (type <T>) be deleted.
     */
    protected AuditedBeanMetaData(String registryKey, boolean deleteable) {
        this.registryKey = registryKey;
        this.deleteable = deleteable;
        columnData = new ArrayList<FieldData<T>>();
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "id"));
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "version"));
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "created.transactionDate"));
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "created.id"));
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "updated.transactionDate"));
        columnData.add(new BaseFieldData<T>(AbstractAuditedBean.class, "auditedBean", "updated.id"));
        // setTableHeader(ResourceFactory.getInstance().getString(getRegistryKey()
        // + ".tableHeader.text"));
    }

    protected Boolean getIncludeDebugInfo() {
        return ContactsState.isDebugMode();
    }

    protected List<FieldData<T>> getColumnData() {
        return columnData;
    }

    // @Override
    // public String getTableHeader() {
    // return tableHeader;
    // }
    //
    // protected void setTableHeader(String tableHeader) {
    // this.tableHeader = tableHeader;
    // }

    @Override
    public String getRegistryKey() {
        return registryKey;
    }

    protected void setRegistryKey(String registryKey) {
        this.registryKey = registryKey;
    }

    @Override
    public boolean isDeleteable() {
        return deleteable;
    }

    protected void setDeleteable(boolean deleteable) {
        this.deleteable = deleteable;
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
