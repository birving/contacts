package com.brmw.contacts.domain.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Locator;

public class LocatorMetaData extends AuditedBeanMetaData<Locator> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(LocatorMetaData.class);
    public static final String REGISTRY_KEY = "locators";

    private List<FieldData<Locator>> locatorColumnData;

    public LocatorMetaData(String prefix) {
//        String key;
        super(prefix + REGISTRY_KEY, true);
        locatorColumnData = new ArrayList<FieldData<Locator>>();
        locatorColumnData.add(new BeanFieldData<Locator>(Locator.class, getTableName(), "medium", true));
        locatorColumnData.add(new BeanFieldData<Locator>(Locator.class, getTableName(), "value", true));
        locatorColumnData.add(new BeanFieldData<Locator>(Locator.class, getTableName(), "notes", true));
    }

    @Override
    protected List<FieldData<Locator>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<Locator>> colData = new ArrayList<FieldData<Locator>>(locatorColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return locatorColumnData;
        }
    }

    @Override
    public Locator createInstance() {
        return new Locator();
    }
}
