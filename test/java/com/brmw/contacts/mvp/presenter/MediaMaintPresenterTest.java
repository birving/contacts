package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.mvp.model.MediaMaintModel;
import com.brmw.contacts.mvp.presenter.MediaMaintPresenter;
import com.brmw.contacts.mvp.view.MediaMaintView;
import com.brmw.contacts.test.jmock.SaveParameterAction;

public class MediaMaintPresenterTest extends MockObjectTestCase {

    private MediaMaintView mockView;
    private MediaMaintModel mockModel;
    private MediaMaintPresenter presenter;
    private Map<String, ActionListener> actionListeners = new HashMap<String, ActionListener>();

    @Override
    protected void setUp() throws Exception {
        mockView = mock(MediaMaintView.class);
        mockModel = mock(MediaMaintModel.class);

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addActionListener(with(any(ActionListener.class)));
                will(SaveParameterAction.saveParameter(0, "mediaMaint", actionListeners));
            }
        });

        presenter = new MediaMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
    }

    @SuppressWarnings("unchecked")
    public void testMediaMaint() {

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockModel).getAllMedia();
                oneOf(mockView).displayMedia(with(any(Collection.class)));
            }
        });

        // Fire event (User just clicked button)
        actionListeners.get("mediaMaint").actionPerformed(new ActionEvent(new Object(), 0, "command"));
        
    }
}
