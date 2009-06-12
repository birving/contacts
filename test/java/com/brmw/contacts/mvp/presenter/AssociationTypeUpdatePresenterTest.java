package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.mvp.model.AssociationTypeUpdateModel;
import com.brmw.contacts.mvp.presenter.AssociationTypeUpdatePresenter;
import com.brmw.contacts.mvp.view.AssociationTypeUpdateView;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;

public class AssociationTypeUpdatePresenterTest extends MockObjectTestCase {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AssociationTypeUpdatePresenterTest.class);
    private AssociationTypeUpdateView mockView;
    private AssociationTypeUpdateModel mockModel;
    private AssociationTypeUpdatePresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(AssociationTypeUpdateView.class);
        mockModel = mock(AssociationTypeUpdateModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "associationTypeUpdate", actionListeners));
            }
        });

        presenter = new AssociationTypeUpdatePresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testUpdateAssociationType() {

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).getAssociationType();
                oneOf(mockModel).updateAllAssociationTypes(with(any(Collection.class)));
                oneOf(mockView).displayAssociationTypes(with(any(Collection.class)));
            }
        });

        SwingWorkerTestPlugin worker = new SwingWorkerTestPlugin(presenter.getWorkerPlugin());
        presenter.setWorkerPlugin(worker);

        // Fire event (Simulates user clicks Save button)
        actionListeners.get("associationTypeUpdate").actionPerformed(new ActionEvent(new Object(), 0, "update"));

        worker.waitForCompletion();
    }
}
