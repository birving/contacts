package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.mvp.model.MediaUpdateModel;
import com.brmw.contacts.mvp.view.MediaUpdateView;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

/**
 * Maintenance functions for media types.
 */
public class MediaUpdatePresenter {
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdatePresenter.class);
    private MediaUpdateView mediaUpdateView;
    private MediaUpdateModel mediaUpdateModel;
    private SwingWorkerPlugin<Collection<Medium>, Object> workerPlugin;

    public MediaUpdatePresenter(MediaUpdateView mediaUpdateView, MediaUpdateModel mediaUpdateModel) {
        this.mediaUpdateView = mediaUpdateView;
        this.mediaUpdateModel = mediaUpdateModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /**
     * Do the required work off of the event dispatch thread
     */
    private void handleMediaUpdateRequest() {
        logger.debug("Calling MediaUpdatePresenter.handleMediaUpdateRequest()");
        Collection<Medium> media = mediaUpdateView.getMedia();
        SwingWorker<Collection<Medium>, Object> swingWorker =
                new PlugableSwingWorker<Collection<Medium>, Object>(workerPlugin);
        workerPlugin.setInitialValue(media);
        swingWorker.execute();
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

    protected void setWorkerPlugin(SwingWorkerPlugin<Collection<Medium>, Object> worker) {
        this.workerPlugin = worker;
    }

    protected SwingWorkerPlugin<Collection<Medium>, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Collection<Medium>, Object> {

        @Override
        public Collection<Medium> doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            return mediaUpdateModel.updateAllMedia(getInitialValue());
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                mediaUpdateView.displayMedia(get());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
