package com.brmw.contacts.domain.adaptor;

import com.brmw.contacts.ResourceFactory;

public abstract class AbstractFieldData<T> implements FieldData<T> {

    private ResourceFactory resourceFactory = ResourceFactory.getInstance();

    private String ownerKey;
    private String fieldKey;
    private boolean fieldEditable;
    private Class<?> fieldClass;

    protected AbstractFieldData(String ownerKey, String fieldKey) {
        this(ownerKey, fieldKey, false);
    }

    protected AbstractFieldData(String ownerKey, String fieldKey, boolean fieldEditable) {
        this(ownerKey, fieldKey, fieldEditable, String.class);
    }

    protected AbstractFieldData(String ownerKey, String fieldKey, boolean fieldEditable, Class<?> fieldClass) {
        this.ownerKey = ownerKey;
        this.fieldKey = fieldKey;
        this.fieldEditable = fieldEditable;
        this.fieldClass = fieldClass;
    }

    /**
     * Set the value for this field. If this field is editable, then this method
     * must be overridden.
     */
    public void setValue(T rowObject, Object value) {
        if (isFieldEditable()) {
            throw new IllegalStateException("Program error: setValue() must be overridden for editable fields.");
        } else {
            throw new UnsupportedOperationException("This field: " + ownerKey + "." + fieldKey + " is imutable");
        }
    }

    /**
     * Get the value object for this field. This method must be overridden.
     */
    public abstract Object getValue(T rowObject);

    protected String getOwnerKey() {
        return ownerKey;
    }

    protected String getFieldKey() {
        return fieldKey;
    }

    @Override
    public String getDisplayName() {
        String resourceKey = getOwnerKey() + ".field." + getFieldKey() + ".text";
        return resourceFactory.getString(resourceKey);
    }

    @Override
    public boolean isFieldEditable() {
        return this.fieldEditable;
    }

    protected void setFieldEditable(Boolean fieldEditable) {
        this.fieldEditable = fieldEditable;
    }

    @Override
    public Class<?> getFieldClass() {
        return fieldClass;
    }

    protected void setFieldClass(Class<?> columnclass) {
        this.fieldClass = columnclass;
    }
}
