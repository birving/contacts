package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.mvp.model.PersonListModel;
import com.brmw.contacts.mvp.view.PersonListView;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;

public class PersonListPresenterTest extends MockObjectTestCase {

    private PersonListView mockView;
    private PersonListModel mockModel;
    private PersonListPresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(PersonListView.class);
        mockModel = mock(PersonListModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "personList", actionListeners));
            }
        });

        presenter = new PersonListPresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testPersonList() {

        SwingWorkerTestPlugin worker = new SwingWorkerTestPlugin(presenter.getWorkerPlugin());
        presenter.setWorkerPlugin(worker);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockModel).getAllPersons();
                oneOf(mockView).displayPersons(with(any(Collection.class)));
            }
        });

        // Fire event - Simulates user clicking button
        actionListeners.get("personList").actionPerformed(new ActionEvent(new Object(), 0, "command"));

        worker.waitForCompletion();
    }
}
