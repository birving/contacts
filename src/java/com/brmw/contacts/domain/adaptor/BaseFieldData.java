package com.brmw.contacts.domain.adaptor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ResourceFactory;

public class BaseFieldData<T> implements FieldData<T> {
    private static final Logger logger = LoggerFactory.getLogger(BaseFieldData.class);

    /**
     * ownerClass is *usually* but not always the same as <T>. This is the class
     * which has field as a member.
     */
    private Class<?> ownerClass;
    private String resourceKey;
    private Class<?> fieldClass;
    private boolean fieldEditable;
    private List<PropertyDescriptor> propDescriptors = new ArrayList<PropertyDescriptor>();
    private ResourceFactory resourceFactory = ResourceFactory.getInstance();

    protected BaseFieldData(Class<?> ownerClass, String registryKey, String fieldKey) {
        this(ownerClass, registryKey, fieldKey, false);
    }

    protected BaseFieldData(Class<?> ownerClass, String registryKey, String fieldKey, Boolean fieldEditable) {
        this.ownerClass = ownerClass;
        this.resourceKey = registryKey + ".col." + fieldKey + ".text";
        setFieldKey(fieldKey);
        this.fieldEditable = fieldEditable;
    }

    @Override
    public String getDisplayName() {
        return resourceFactory.getString(resourceKey);
    }

    private void setFieldKey(String fieldKey) {

        String[] fieldNames = fieldKey.split("\\.");

        // Start at the top
        fieldClass = ownerClass;

        for (String fieldName : fieldNames) {
            logger.debug("Getting PropertyDescriptor for class:{}; field:{}", fieldClass, fieldName);
            // Set field & fieldClass if possible.
            // If this fails, then setFieldClass() must be called!
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, fieldClass);
                propDescriptors.add(descriptor);
                // Get next type
                fieldClass = descriptor.getPropertyType();

            } catch (IntrospectionException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Unable to get PropertyDescriptor(s) for " + fieldClass + "; field "
                        + fieldName, e);
            }
        }
    }

    @Override
    public Class<?> getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(Class<?> columnclass) {
        this.fieldClass = columnclass;
    }

    @Override
    public boolean isFieldEditable() {
        return this.fieldEditable;
    }

    public void setFieldEditable(Boolean fieldEditable) {
        this.fieldEditable = fieldEditable;
    }

    @Override
    public Object getValue(T rowObject) {
        Object nextObject = rowObject;
        for (PropertyDescriptor descriptor : propDescriptors) {
            logger.debug("Getting PropertyDescriptor for class:{}; field:{}", ownerClass, resourceKey);
            Method getter = descriptor.getReadMethod();
            if (getter == null) {
                throw new RuntimeException("No getter found for class:" + ownerClass + "; field:" + resourceKey);
            }
            try {
                nextObject = getter.invoke(nextObject);
                if (nextObject == null) {
                    return null;
                }
                // } catch (IllegalArgumentException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + resourceKey, e);
            } catch (InvocationTargetException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + resourceKey, e);
            } catch (NullPointerException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + resourceKey, e);
            }
        }
        return nextObject;
    }

    @Override
    /*
     * Default to read-only column; Override if should be write-able.
     */
    public void setValue(T rowObject, Object value) {
        if (!isFieldEditable()) {
            throw new UnsupportedOperationException();
        } else {
            Object nextObject = rowObject;

            List<PropertyDescriptor> propTreeDescriptors = propDescriptors.subList(0, propDescriptors.size() - 1);

            for (PropertyDescriptor descriptor : propTreeDescriptors) {
                Method getter = descriptor.getReadMethod();
                try {
                    nextObject = getter.invoke(nextObject);
                    // } catch (IllegalArgumentException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO re-throw as custom RuntimeException
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    // TODO re-throw as custom RuntimeException
                    throw new RuntimeException(e);
                }
            }

            // Get the last setter in the list
            Method setter = propDescriptors.get(propDescriptors.size() - 1).getWriteMethod();
            try {
                setter.invoke(nextObject, value);
                // } catch (IllegalArgumentException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException(e);
            }
        }
    }
}
