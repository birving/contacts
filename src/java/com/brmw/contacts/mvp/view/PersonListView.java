package com.brmw.contacts.mvp.view;

import java.util.Collection;

import com.brmw.contacts.domain.Person;

public interface PersonListView extends ButtonView {

    public void displayPersons(Collection<Person> collection);

}
