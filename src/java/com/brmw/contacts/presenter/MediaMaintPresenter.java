package com.brmw.contacts.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.MediaMaintModel;
import com.brmw.contacts.view.MediaMaintView;

/**
 *  Maintenance functions for media types.
 *
 */
public class MediaMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintPresenter.class); 
    private MediaMaintView mediaMaintView;
    private MediaMaintModel mediaMaintModel;

    public MediaMaintPresenter(MediaMaintView mediaMaintView, MediaMaintModel mediaMaintModel) {
        this.mediaMaintView = mediaMaintView;
        this.mediaMaintModel = mediaMaintModel;
    }

    
}
