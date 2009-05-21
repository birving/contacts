package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.model.MediaMaintModel;
import com.brmw.contacts.test.jmock.SaveParameterAction;
import com.brmw.contacts.view.MediaMaintView;

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

//    @SuppressWarnings("unchecked")
//    public void testUpdateMedia() {
//
//        // Expectations
//        checking(new Expectations() {
//            {
//                oneOf(mockView).getMedia();
//                oneOf(mockModel).updateAllMedia(with(any(Collection.class)));
//                oneOf(mockView).displayMedia(with(any(Collection.class)));
//            }
//        });
//
//        // Fire event (User just clicked Save button)
//        actionListeners.get("mediaUpdate").actionPerformed(new ActionEvent(new Object(), 0,
//         "update"));
//    }
}
