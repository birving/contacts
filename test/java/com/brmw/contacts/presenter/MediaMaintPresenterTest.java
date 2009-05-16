package com.brmw.contacts.presenter;

import java.awt.event.ActionListener;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.model.MediaMaintModel;
import com.brmw.contacts.view.MediaMaintView;

public class MediaMaintPresenterTest extends MockObjectTestCase {

    private MediaMaintView mockView;
    private MediaMaintModel mockModel;
    private MediaMaintPresenter presenter;
    private ActionListener actionListener;

    @Override
    protected void setUp() throws Exception {
        mockView = mock(MediaMaintView.class);
        mockModel = mock(MediaMaintModel.class);
 
        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).addAllMediaRequestListener(with(any(ActionListener.class)));
            }
        });

        presenter = new MediaMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
        actionListener = presenter.actionListener;
        assertNotNull(actionListener);
    }

    public void testLoadAllMedia() {
//        actionListener.actionPerformed(new ActionEvent());
    }

}
