package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.model.AssociationMaintModel;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;
import com.brmw.contacts.view.AssociationMaintView;

public class AssociationMaintPresenterTest extends MockObjectTestCase {

    private AssociationMaintView mockView;
    private AssociationMaintModel mockModel;
    private AssociationMaintPresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(AssociationMaintView.class);
        mockModel = mock(AssociationMaintModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "associationMaint", actionListeners));
            }
        });

        presenter = new AssociationMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testAssociationMaint() {

        SwingWorkerTestPlugin worker = new SwingWorkerTestPlugin(presenter.getWorkerPlugin());
        presenter.setWorkerPlugin(worker);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockModel).getAllAssociations();
                oneOf(mockView).displayAssociations(with(any(Collection.class)));
            }
        });

        // Fire event - Simulates user clicking button
        actionListeners.get("associationMaint").actionPerformed(new ActionEvent(new Object(), 0, "command"));
        
        worker.waitForCompletion();
    }
}
