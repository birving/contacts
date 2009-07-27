package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.domain.adaptor.PersonListMetaData;
import com.brmw.contacts.mvp.view.PersonListView;
import com.brmw.contacts.swing.CollectionTableDisplay;

/**
 * Person List GUI elements.
 * 
 */
public class PersonListViewImpl extends AbstractButtonView implements PersonListView {
    private static final Logger logger = LoggerFactory.getLogger(PersonListViewImpl.class);

    public PersonListViewImpl(AbstractButton personListButton) {
        super(personListButton);
    }

    @Override
    public void displayPersons(Collection<Person> person) {
        logger.debug("Running PersonListViewImpl.displayPersons();");
        CollectionTableDisplay<Person> personListDisplay = new CollectionTableDisplay<Person>(person, new PersonListMetaData()) {
//            @Override
//            protected void registerSaveButton(AbstractButton button) {
//                PresenterFirstRegistry.getInstance().registerPersonListButton(button);
//            }

//            @Override
//            protected void registerRevertButton(AbstractButton button) {
//                PresenterFirstRegistry.getInstance().registerPersonListButton(button);
//            }
        };
        personListDisplay.display();
    }
}
