package com.brmw.contacts.mvp.model;

import com.brmw.contacts.domain.Person;

public interface PersonMaintModel {

    // public Collection<Person> getAllPersons();

    public Person getPerson(Long id);
}
