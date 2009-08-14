package com.brmw.contacts.mvp.view.swingimpl;

import javax.swing.ListSelectionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.mvp.view.PersonMaintView;
import com.brmw.contacts.swing.PersonDetail;
import com.brmw.contacts.swing.SelectableList;

public class PersonMaintViewImpl extends AbstractListSelectionView<Person> implements PersonMaintView {
    private static final Logger logger = LoggerFactory.getLogger(PersonMaintViewImpl.class);

    public PersonMaintViewImpl(ListSelectionModel listSelectionModel, SelectableList<Person> selectableList) {
        super(listSelectionModel, selectableList);
    }

    @Override
    public void displayPerson(Person person) {
        logger.debug("Running PersonMaintViewImpl.displayPerson();");
        PersonDetail personDetail = new PersonDetail();
        personDetail.setPerson(person);
        personDetail.display();
    }
}
