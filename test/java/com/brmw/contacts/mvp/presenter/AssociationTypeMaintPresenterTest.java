package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.mvp.model.AssociationTypeMaintModel;
import com.brmw.contacts.mvp.presenter.AssociationTypeMaintPresenter;
import com.brmw.contacts.mvp.view.AssociationTypeMaintView;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;

public class AssociationTypeMaintPresenterTest extends MockObjectTestCase {

    private AssociationTypeMaintView mockView;
    private AssociationTypeMaintModel mockModel;
    private AssociationTypeMaintPresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(AssociationTypeMaintView.class);
        mockModel = mock(AssociationTypeMaintModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "associationTypeMaint", actionListeners));
            }
        });

        presenter = new AssociationTypeMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testAssociationTypeMaint() {

        SwingWorkerTestPlugin worker = new SwingWorkerTestPlugin(presenter.getWorkerPlugin());
        presenter.setWorkerPlugin(worker);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockModel).getAllAssociationTypes();
                oneOf(mockView).displayAssociationTypes(with(any(Collection.class)));
            }
        });

        // Fire event - Simulates user clicking button
        actionListeners.get("associationTypeMaint").actionPerformed(new ActionEvent(new Object(), 0, "command"));
        
        worker.waitForCompletion();
    }
}
