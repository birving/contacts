package com.brmw.contacts.domain.adaptor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;

public class PersonListMetaData extends AuditedBeanMetaData<Person> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(PersonListMetaData.class);
    public static final String REGISTRY_KEY = "personMaint";

    private List<FieldData<Person>> personColumnData;

    public PersonListMetaData() {
        super(REGISTRY_KEY, true);
        personColumnData = new ArrayList<FieldData<Person>>();
        personColumnData.add(new BaseFieldData<Person>(Person.class, getTableName(), "firstName"));
        personColumnData.add(new BaseFieldData<Person>(Person.class, getTableName(), "lastName"));
        personColumnData.add(new BaseFieldData<Person>(Person.class, getTableName(), "company"));
        personColumnData.add(new BaseFieldData<Person>(Person.class, getTableName(), "role"));
        // personColumnData.add(new BaseFieldData<Person>(Person.class,
        // getTableName(), "phone"));
        // personColumnData.add(new BaseFieldData<Person>(Person.class,
        // getTableName(), "email"));
        personColumnData.add(new BaseFieldData<Person>(Person.class, getTableName(), "notes"));
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
