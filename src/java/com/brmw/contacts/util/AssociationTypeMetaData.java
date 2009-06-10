package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;

public class AssociationTypeMetaData extends AuditedBeanMetaData<AssociationType> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AssociationTypeMetaData.class);

    private static final FieldData<AssociationType> NAME_COLUMN = new AbstractFieldData<AssociationType>(AssociationType.class, "name", true);
    private static final FieldData<AssociationType> DESCRIPTION_COLUMN = new AbstractFieldData<AssociationType>(AssociationType.class, "description",
            true);

    @SuppressWarnings("unchecked")
    private List<FieldData<AssociationType>> associationTypeColumnData = Arrays.asList(NAME_COLUMN, DESCRIPTION_COLUMN);

    @Override
    public String getRegistryKey() {
        return "associationTypeMaint";
    }

    @Override
    public String getTableHeader() {
        return "Define company association Types";
    }

    @Override
    public List<FieldData<AssociationType>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<AssociationType>> colData = new ArrayList<FieldData<AssociationType>>(associationTypeColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return associationTypeColumnData;
        }
    }

    @Override
    public boolean isDeleteable() {
        return true;
    }

    @Override
    public AssociationType createInstance() {
        return new AssociationType();
    }
}
