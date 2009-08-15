package com.brmw.contacts.domain.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Locator;
import com.brmw.contacts.domain.Person;

public class PersonListMetaData extends AuditedBeanMetaData<Person> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(PersonListMetaData.class);
    public static final String REGISTRY_KEY = "personMaint";

    private List<FieldData<Person>> personColumnData;

    public PersonListMetaData() {
        super(REGISTRY_KEY, true);
        personColumnData = new ArrayList<FieldData<Person>>();
        personColumnData.add(new BeanFieldData<Person>(Person.class, getTableName(), "displayName"));
//        personColumnData.add(new BeanFieldData<Person>(Person.class, getTableName(), "uniqueName"));
        personColumnData.add(new BeanFieldData<Person>(Person.class, getTableName(), "company"));
        personColumnData.add(new BeanFieldData<Person>(Person.class, getTableName(), "role"));

        personColumnData.add(new AbstractFieldData<Person>(getTableName(), "email") {
            @Override
            public Object getValue(Person person) {
                for (Locator locator : person.getLocators()) {
                    if ("email".equals(locator.getMedium().getType())) {
                        return locator.getValue();
                    }
                }
                return null;
            }
        });

        personColumnData.add(new AbstractFieldData<Person>(getTableName(), "phone") {
            @Override
            public Object getValue(Person person) {
                for (Locator locator : person.getLocators()) {
                    if ("phone".equals(locator.getMedium().getType())) {
                        return locator.getValue();
                    }
                }
                return null;
            }
        });
        personColumnData.add(new BeanFieldData<Person>(Person.class, getTableName(), "notes"));
    }

    @Override
    protected List<FieldData<Person>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<FieldData<Person>> colData = new ArrayList<FieldData<Person>>(personColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return personColumnData;
        }
    }

    @Override
    public Person createInstance() {
        return new Person();
    }
}
