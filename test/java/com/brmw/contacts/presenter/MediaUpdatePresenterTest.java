package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.MediaUpdateModel;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.test.swing.SwingWorkerTestPlugin;
import com.brmw.contacts.view.MediaUpdateView;

public class MediaUpdatePresenterTest extends MockObjectTestCase {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdatePresenterTest.class);
    private MediaUpdateView mockView;
    private MediaUpdateModel mockModel;
    private MediaUpdatePresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(MediaUpdateView.class);
        mockModel = mock(MediaUpdateModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "mediaUpdate", actionListeners));
            }
        });

        presenter = new MediaUpdatePresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testUpdateMedia() {

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).getMedia();
                oneOf(mockModel).updateAllMedia(with(any(Collection.class)));
                oneOf(mockView).displayMedia(with(any(Collection.class)));
            }
        });

        SwingWorkerTestPlugin worker = new SwingWorkerTestPlugin(presenter.new Worker());
        presenter.setWorker(worker);

        // Fire event (Simulates user clicks Save button)
        actionListeners.get("mediaUpdate").actionPerformed(new ActionEvent(new Object(), 0, "update"));

        worker.waitForCompletion();
    }
}
