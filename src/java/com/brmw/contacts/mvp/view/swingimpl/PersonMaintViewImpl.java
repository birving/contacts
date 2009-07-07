package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.domain.adaptor.PersonListMetaData;
import com.brmw.contacts.mvp.view.PersonMaintView;
import com.brmw.contacts.swing.PersonDetail;

public class PersonMaintViewImpl extends AbstractButtonView implements PersonMaintView {
    private static final Logger logger = LoggerFactory.getLogger(PersonMaintViewImpl.class);

    public PersonMaintViewImpl(AbstractButton button) {
        super(button);
    }

    @Override
    public void displayPersons(Collection<Person> people) {
        logger.debug("Running PersonMaintViewImpl.displayPersons();");
//        CollectionTableDisplay<Person> personDisplay = new CollectionTableDisplay<Person>(people, new PersonListMetaData());
//        personDisplay.display();
        for (Person person: people) {
            PersonDetail personDetail = new PersonDetail();
            personDetail.setPerson(person);
            personDetail.display();
        }
    }
}
