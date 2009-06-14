package com.brmw.contacts.domain.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;

public class MediumMetaData extends AuditedBeanMetaData<Medium> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MediumMetaData.class);
    public static final String REGISTRY_KEY = "mediaMaint";

    private List<FieldData<Medium>> mediumColumnData;

    public MediumMetaData() {
        super(REGISTRY_KEY, true);
        mediumColumnData = new ArrayList<FieldData<Medium>>();
        mediumColumnData.add(new BaseFieldData<Medium>(Medium.class, getTableName(), "name", true));
        mediumColumnData.add(new BaseFieldData<Medium>(Medium.class, getTableName(), "type", true));
        mediumColumnData.add(new BaseFieldData<Medium>(Medium.class, getTableName(), "notes", true));
    }

    @Override
    protected List<FieldData<Medium>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<Medium>> colData = new ArrayList<FieldData<Medium>>(mediumColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return mediumColumnData;
        }
    }

    @Override
    public Medium createInstance() {
        return new Medium();
    }
}
