package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.mvp.model.AssociationTypeUpdateModel;
import com.brmw.contacts.mvp.view.AssociationTypeUpdateView;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

/**
 * Maintenance functions for Association types.
 */
public class AssociationTypeUpdatePresenter {
    private static final Logger logger = LoggerFactory.getLogger(AssociationTypeUpdatePresenter.class);
    private AssociationTypeUpdateView associationTypeUpdateView;
    private AssociationTypeUpdateModel associationTypeUpdateModel;
    private SwingWorkerPlugin<Collection<AssociationType>, Object> workerPlugin;

    public AssociationTypeUpdatePresenter(AssociationTypeUpdateView associationTypeUpdateView,
            AssociationTypeUpdateModel associationTypeUpdateModel) {
        this.associationTypeUpdateView = associationTypeUpdateView;
        this.associationTypeUpdateModel = associationTypeUpdateModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /**
     * Do the required work off of the event dispatch thread
     */
    private void handleAssociationTypeUpdateRequest() {
        logger.debug("Calling AssociationTypeUpdatePresenter.handleAssociationTypeUpdateRequest()");
        Collection<AssociationType> associationTypes = associationTypeUpdateView.getAssociationType();
        SwingWorker<Collection<AssociationType>, Object> swingWorker =
                new PlugableSwingWorker<Collection<AssociationType>, Object>(workerPlugin);
        workerPlugin.setInitialValue(associationTypes);
        swingWorker.execute();
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to save updates from AssociationType Maintenance screen
        associationTypeUpdateView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAssociationTypeUpdateRequest();
            }
        });
    }

    protected void setWorkerPlugin(SwingWorkerPlugin<Collection<AssociationType>, Object> worker) {
        this.workerPlugin = worker;
    }

    protected SwingWorkerPlugin<Collection<AssociationType>, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Collection<AssociationType>, Object> {

        @Override
        public Collection<AssociationType> doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            return associationTypeUpdateModel.updateAllAssociationTypes(getInitialValue());
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                associationTypeUpdateView.displayAssociationTypes(get());
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
