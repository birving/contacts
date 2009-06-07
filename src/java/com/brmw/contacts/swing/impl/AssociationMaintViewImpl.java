package com.brmw.contacts.swing.impl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Association;
import com.brmw.contacts.view.AssociationMaintView;

/**
 * Media Maintenance GUI elements.
 * 
 */
public class AssociationMaintViewImpl extends AbstractButtonView implements AssociationMaintView {
    private static final Logger logger = LoggerFactory.getLogger(AssociationMaintViewImpl.class);

    public AssociationMaintViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayAssociations(Collection<Association> media) {
        logger.debug("Running AssociationMaintViewImpl.displayMedia();" );
        // TODO: implement method
        // new AssociationMaintDisplay(media).display();
    }
}
