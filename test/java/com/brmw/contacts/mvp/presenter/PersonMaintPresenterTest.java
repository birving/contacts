package com.brmw.contacts.mvp.presenter;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.mvp.model.PersonMaintModel;
import com.brmw.contacts.mvp.view.PersonMaintView;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;

public class PersonMaintPresenterTest extends MockObjectTestCase {
    private PersonMaintPresenter presenter;
    private PersonMaintView mockView;
    private PersonMaintModel mockModel;
    private Map<String, ListSelectionListener> listSelectionListeners = new HashMap<String, ListSelectionListener>();

    protected void setUp() throws Exception {
        mockView = mock(PersonMaintView.class);
        mockModel = mock(PersonMaintModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addListSelectionListener(with(any(ListSelectionListener.class)));
                will(SaveParameterAction.saveParameter(0, "personMaint", listSelectionListeners));
            }
        });

        presenter = new PersonMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    public void testPersonMaint() {

        final Person selectedPerson = new Person();
        selectedPerson.setDisplayName("Joe Schmoe");
        selectedPerson.setUniqueName("Schmoe, Joe");

        final Person persistedPerson = new Person();
        persistedPerson.setDisplayName("Mark Mywords");
        persistedPerson.setUniqueName("Mywords, Mark");

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).getSelected();
                will(returnValue(selectedPerson));
                oneOf(mockModel).getPerson(with(any(Long.class)));
                will(returnValue(persistedPerson));
                oneOf(mockView).displayPerson(with(any(Person.class)));
            }
        });

        SwingWorkerTestPlugin<Person, Object> worker =
                new SwingWorkerTestPlugin<Person, Object>(presenter.getWorkerPlugin());
        presenter.setWorkerPlugin(worker);

        listSelectionListeners.get("personMaint").valueChanged(
                                                               new ListSelectionEvent(new DefaultListSelectionModel(),
                                                                       0, 0, false));
        worker.waitForCompletion();
    }
}
