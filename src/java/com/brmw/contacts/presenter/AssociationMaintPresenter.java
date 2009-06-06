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
import com.brmw.contacts.view.AssociationMaintView;

/**
 * Maintenance functions for media types.
 */
public class AssociationMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(AssociationMaintPresenter.class);
    private AssociationMaintView associationMaintView;
    private AssociationMaintModel associationMaintModel;

    public AssociationMaintPresenter(AssociationMaintView associationMaintView, AssociationMaintModel associationMaintModel) {
        this.associationMaintView = associationMaintView;
        this.associationMaintModel = associationMaintModel;
        addListeners();
    }

    /*
     * TODO: This should be moved off of the event dispatch thread!!!
     */
    private void handleAssociationMaintRequest() {
        logger.debug("Calling AssociationMaintPresenter.handleAssociationMaintRequest()");

        new Worker().execute();
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

    private class Worker extends SwingWorker<Collection<Association>, Object> {
        @Override
        protected Collection<Association> doInBackground() throws Exception {
            return associationMaintModel.getAllAssociations();
        }

        @Override
        protected void done() {
            try {
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
