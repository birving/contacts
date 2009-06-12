package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.mvp.model.AssociationTypeMaintModel;
import com.brmw.contacts.mvp.view.AssociationTypeMaintView;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

/**
 * Maintenance functions for media types.
 */
public class AssociationTypeMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(AssociationTypeMaintPresenter.class);
    private AssociationTypeMaintView associationTypeMaintView;
    private AssociationTypeMaintModel associationTypeMaintModel;
    private SwingWorkerPlugin<Collection<AssociationType>, Object> workerPlugin;

    public AssociationTypeMaintPresenter(AssociationTypeMaintView associationTypeMaintView, AssociationTypeMaintModel associationTypeMaintModel) {
        this.associationTypeMaintView = associationTypeMaintView;
        this.associationTypeMaintModel = associationTypeMaintModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /*
     * TODO: This should be moved off of the event dispatch thread!!!
     */
    private void handleAssociationTypeMaintRequest() {
        logger.debug("Calling AssociationTypeMaintPresenter.handleAssociationTypeMaintRequest()");
        SwingWorker<Collection<AssociationType>, Object> swingWorker = new PlugableSwingWorker<Collection<AssociationType>, Object>(workerPlugin);
        swingWorker.execute();
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to go to Media Maintenance screen
        associationTypeMaintView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAssociationTypeMaintRequest();
            }
        });
    }

    protected SwingWorkerPlugin<Collection<AssociationType>, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    protected void setWorkerPlugin(SwingWorkerPlugin<Collection<AssociationType>, Object> workerPlugin) {
        this.workerPlugin = workerPlugin;
    }

    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Collection<AssociationType>, Object> {
        @Override
        public Collection<AssociationType> doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            return associationTypeMaintModel.getAllAssociationTypes();
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                associationTypeMaintView.displayAssociationTypes(get());
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
