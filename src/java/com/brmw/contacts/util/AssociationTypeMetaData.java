package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;

public class AssociationTypeMetaData extends AuditedBeanMetaData<AssociationType> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AssociationTypeMetaData.class);
    public static final String REGISTRY_KEY = "assocTypeMaint";

    private List<FieldData<AssociationType>> associationTypeColumnData;

    public AssociationTypeMetaData() {
        super(REGISTRY_KEY, true);
        associationTypeColumnData = new ArrayList<FieldData<AssociationType>>();
        associationTypeColumnData.add(new AbstractFieldData<AssociationType>(AssociationType.class, getRegistryKey(), "name", true));
        associationTypeColumnData.add(new AbstractFieldData<AssociationType>(AssociationType.class, getRegistryKey(), "description", true));
    }

    @Override
    protected List<FieldData<AssociationType>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<AssociationType>> colData = new ArrayList<FieldData<AssociationType>>(associationTypeColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return associationTypeColumnData;
        }
    }

    @Override
    public AssociationType createInstance() {
        return new AssociationType();
    }
}
