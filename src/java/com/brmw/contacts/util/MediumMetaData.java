package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;

public class MediumMetaData extends AuditedBeanMetaData<Medium> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MediumMetaData.class);

    private static final FieldData<Medium> NAME_COLUMN = new AbstractFieldData<Medium>(Medium.class, "name", true);
    private static final FieldData<Medium> TYPE_COLUMN = new AbstractFieldData<Medium>(Medium.class, "type", true);
    private static final FieldData<Medium> NOTES_COLUMN = new AbstractFieldData<Medium>(Medium.class, "notes", true);

    @SuppressWarnings("unchecked")
    private List<FieldData<Medium>> mediumColumnData = Arrays.asList(NAME_COLUMN, TYPE_COLUMN, NOTES_COLUMN);

    @Override
    public String getRegistryKey() {
        return "mediaMaint";
    }

    @Override
    public String getTableHeader() {
        return "Define media";
    }

    @Override
    public List<FieldData<Medium>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<Medium>> colData = new ArrayList<FieldData<Medium>>(mediumColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return mediumColumnData;
        }
    }

    @Override
    public boolean isDeleteable() {
        return true;
    }

    @Override
    public Medium createInstance() {
        return new Medium();
    }
}
