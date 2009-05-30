package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.model.MediaUpdateModel;
import com.brmw.contacts.view.MediaUpdateView;

/**
 * Maintenance functions for media types.
 */
public class MediaUpdatePresenter {
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdatePresenter.class);
    private MediaUpdateView mediaUpdateView;
    private MediaUpdateModel mediaUpdateModel;

    public MediaUpdatePresenter(MediaUpdateView mediaUpdateView, MediaUpdateModel mediaUpdateModel) {
        this.mediaUpdateView = mediaUpdateView;
        this.mediaUpdateModel = mediaUpdateModel;
        addListeners();
    }

    /*
     * TODO: This should be moved off of the event dispatch thread!!!
     */
    private void handleMediaUpdateRequest() {
        logger.debug("Calling MediaUpdatePresenter.handleMediaUpdateRequest()");
        Collection<Medium> media = mediaUpdateView.getMedia();
        new Worker(media).execute();
        // mediaUpdateView.displayMedia(mediaUpdateModel.updateAllMedia(media));
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to save updates from Media Maintenance screen
        mediaUpdateView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleMediaUpdateRequest();
            }
        });
    }

    private class Worker extends SwingWorker<Collection<Medium>, Object> {
        private Collection<Medium> media;
        
        protected Worker(Collection<Medium> media) {
            this.media = media;
        }

        @Override
        protected Collection<Medium> doInBackground() throws Exception {
            return mediaUpdateModel.updateAllMedia(media);
        }

        @Override
        protected void done() {
            try {
                mediaUpdateView.displayMedia(get());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
