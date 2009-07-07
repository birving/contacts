package com.brmw.contacts.domain.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;

public class AssociationTypeMetaData extends AuditedBeanMetaData<AssociationType> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(AssociationTypeMetaData.class);
    public static final String REGISTRY_KEY = "assocTypeMaint";

    private List<FieldData<AssociationType>> assocTypeColumnData;

    public AssociationTypeMetaData() {
        super(REGISTRY_KEY, true);
        assocTypeColumnData = new ArrayList<FieldData<AssociationType>>();
        assocTypeColumnData.add(new BeanFieldData<AssociationType>(AssociationType.class, getTableName(), "name", true));
        assocTypeColumnData.add(new BeanFieldData<AssociationType>(AssociationType.class, getTableName(),
                "description", true));
    }

    @Override
    protected List<FieldData<AssociationType>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<AssociationType>> colData = new ArrayList<FieldData<AssociationType>>(assocTypeColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return assocTypeColumnData;
        }
    }

    @Override
    public AssociationType createInstance() {
        return new AssociationType();
    }
}
