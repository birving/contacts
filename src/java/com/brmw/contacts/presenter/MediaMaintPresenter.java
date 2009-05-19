package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.model.MediaMaintModel;
import com.brmw.contacts.view.MediaMaintView;

/**
 * Maintenance functions for media types.
 */
public class MediaMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintPresenter.class);
    private MediaMaintView mediaMaintView;
    private MediaMaintModel mediaMaintModel;
    protected ActionListener actionListener;

    public MediaMaintPresenter(MediaMaintView mediaMaintView, MediaMaintModel mediaMaintModel) {
        this.mediaMaintView = mediaMaintView;
        this.mediaMaintModel = mediaMaintModel;
        addListeners();
    }

    private void handleAllMediaRequest() {
        logger.debug("Calling handleAllMediaRequest()");

        // Add some test data ...
        Collection<Medium> tableData = new ArrayList<Medium>();
        Medium medium1 = new Medium();
        medium1.setName("Primary email");
        medium1.setType("email");
        tableData.add(medium1);

        Medium medium2 = new Medium();
        medium2.setName("Another email");
        medium2.setType("email");
        tableData.add(medium2);

        Medium medium3 = new Medium();
        medium2.setName("Home phone");
        medium2.setType("phone");
        tableData.add(medium3);

        mediaMaintView.displayMedia(tableData);
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        mediaMaintView.addAllMediaRequestListener(actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAllMediaRequest();
            }
        });
    }

}
