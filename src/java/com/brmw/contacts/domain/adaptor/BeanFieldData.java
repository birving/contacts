package com.brmw.contacts.domain.adaptor;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of FieldData using bean introspection to get and set values
 * 
 * @author bruce
 * 
 * @param <T>
 */
public class BeanFieldData<T> extends AbstractFieldData<T> implements FieldData<T> {
    private static final Logger logger = LoggerFactory.getLogger(BeanFieldData.class);

    /**
     * ownerClass is *usually* but not always the same as <T>. This is the class
     * which has field as a member.
     */
    private Class<?> ownerClass;
    private List<PropertyDescriptor> propDescriptors = new ArrayList<PropertyDescriptor>();

    protected BeanFieldData(Class<?> ownerClass, String ownerKey, String fieldKey) {
        this(ownerClass, ownerKey, fieldKey, false);
    }

    protected BeanFieldData(Class<?> ownerClass, String ownerKey, String fieldKey, Boolean fieldEditable) {
        super(ownerKey, fieldKey, fieldEditable);
        this.ownerClass = ownerClass;
        setFieldKey(fieldKey);
    }

    private void setFieldKey(String fieldKey) {

        String[] fieldNames = fieldKey.split("\\.");

        // Start at the top
        setFieldClass(ownerClass);

        for (String fieldName : fieldNames) {
            logger.debug("Getting PropertyDescriptor for class:{}; field:{}", getFieldClass(), fieldName);
            // Set field & fieldClass if possible.
            // If this fails, then setFieldClass() must be called!
            try {
                PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, getFieldClass());
                propDescriptors.add(descriptor);
                // Get next type
                setFieldClass(descriptor.getPropertyType());

            } catch (IntrospectionException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Unable to get PropertyDescriptor(s) for " + getFieldClass() + "; field "
                        + fieldName, e);
            }
        }
    }

    @Override
    public Object getValue(T rowObject) {
        Object nextObject = rowObject;
        for (PropertyDescriptor descriptor : propDescriptors) {
            logger.debug("Getting PropertyDescriptor for class:{}; field:{}", ownerClass, getFieldKey());
            Method getter = descriptor.getReadMethod();
            if (getter == null) {
                throw new RuntimeException("No getter found for class:" + ownerClass + "; field:" + getFieldKey());
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
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + getFieldKey(),
                        e);
            } catch (InvocationTargetException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + getFieldKey(),
                        e);
            } catch (NullPointerException e) {
                // TODO re-throw as custom RuntimeException
                throw new RuntimeException("Unable to get value from class:" + ownerClass + "; field:" + getFieldKey(),
                        e);
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
