package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import org.jmock.Expectations;
import org.jmock.integration.junit3.MockObjectTestCase;

import com.brmw.contacts.domain.Medium;
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
                oneOf(mockView).addMediaMaintRequestListener(with(any(ActionListener.class)));
            }
        });

        presenter = new MediaMaintPresenter(mockView, mockModel);
        assertNotNull(presenter);
        actionListener = presenter.actionListener;
        assertNotNull(actionListener);
    }

    public void testLoadMediaMaint() {

        Collection<Medium> tableData = new ArrayList<Medium>();

        // Expectations
        checking(new Expectations() {
            {
                oneOf(mockView).displayMedia(with(any(Collection.class)));
                oneOf(mockModel).getAllMedia();
            }
        });

        // Fire event (User just clicked button)
        actionListener.actionPerformed(new ActionEvent(new Object(), 0, "command"));

    }

}
