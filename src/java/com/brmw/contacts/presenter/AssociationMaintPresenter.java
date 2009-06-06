package com.brmw.contacts.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Association;
import com.brmw.contacts.model.AssociationMaintModel;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;
import com.brmw.contacts.view.AssociationMaintView;

/**
 * Maintenance functions for media types.
 */
public class AssociationMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(AssociationMaintPresenter.class);
    private AssociationMaintView associationMaintView;
    private AssociationMaintModel associationMaintModel;
    private SwingWorkerPlugin<Collection<Association>, Object> workerPlugin;

    public AssociationMaintPresenter(AssociationMaintView associationMaintView, AssociationMaintModel associationMaintModel) {
        this.associationMaintView = associationMaintView;
        this.associationMaintModel = associationMaintModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /*
     * TODO: This should be moved off of the event dispatch thread!!!
     */
    private void handleAssociationMaintRequest() {
        logger.debug("Calling AssociationMaintPresenter.handleAssociationMaintRequest()");
        SwingWorker<Collection<Association>, Object> swingWorker = new PlugableSwingWorker<Collection<Association>, Object>(workerPlugin);
        swingWorker.execute();
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to go to Media Maintenance screen
        associationMaintView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleAssociationMaintRequest();
            }
        });
    }

    
    public SwingWorkerPlugin<Collection<Association>, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    public void setWorkerPlugin(SwingWorkerPlugin<Collection<Association>, Object> workerPlugin) {
        this.workerPlugin = workerPlugin;
    }


    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Collection<Association>, Object> {
        @Override
        public Collection<Association> doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            return associationMaintModel.getAllAssociations();
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                associationMaintView.displayAssociations(get());
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
