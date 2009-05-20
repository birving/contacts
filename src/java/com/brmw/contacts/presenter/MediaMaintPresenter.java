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

    private void handleMediaMaintRequest() {
        logger.debug("Calling MediaMaintPresenter.handleMediaMaintRequest()");
        
        mediaMaintView.displayMedia(mediaMaintModel.getAllMedia());
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        mediaMaintView.addMediaMaintRequestListener(actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleMediaMaintRequest();
            }
        });
    }

}
