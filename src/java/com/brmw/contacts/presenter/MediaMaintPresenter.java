package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.MediaMaintModel;
import com.brmw.contacts.view.MediaMaintView;

/**
 * Maintenance functions for media types.
 */
public class MediaMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintPresenter.class);
    private MediaMaintView mediaMaintView;
    private MediaMaintModel mediaMaintModel;

    public MediaMaintPresenter(MediaMaintView mediaMaintView, MediaMaintModel mediaMaintModel) {
        this.mediaMaintView = mediaMaintView;
        this.mediaMaintModel = mediaMaintModel;
        addListeners();
    }

    /*
     * TODO: This should be moved off of the event dispatch thread!!!
     */
    private void handleMediaMaintRequest() {
        logger.debug("Calling MediaMaintPresenter.handleMediaMaintRequest()");

        mediaMaintView.displayMedia(mediaMaintModel.getAllMedia());
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to go to Media Maintenance screen
        mediaMaintView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleMediaMaintRequest();
            }
        });
    }
}
