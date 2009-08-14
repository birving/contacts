package com.brmw.contacts.mvp.view;

import com.brmw.contacts.domain.Person;

public interface PersonMaintView extends ListSelectionView<Person> {

    public void displayPerson(Person person);

}
