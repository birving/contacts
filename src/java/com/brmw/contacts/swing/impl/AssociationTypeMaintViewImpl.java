package com.brmw.contacts.swing.impl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.swing.CollectionTableDisplay;
import com.brmw.contacts.util.AssociationTypeMetaData;
import com.brmw.contacts.view.AssociationTypeMaintView;

/**
 * Association Type Maintenance GUI elements.
 * 
 */
public class AssociationTypeMaintViewImpl extends AbstractButtonView implements AssociationTypeMaintView {
    private static final Logger logger = LoggerFactory.getLogger(AssociationTypeMaintViewImpl.class);

    public AssociationTypeMaintViewImpl(AbstractButton associationTypeMaintButton) {
        super(associationTypeMaintButton);
    }

    @Override
    public void displayAssociationTypes(Collection<AssociationType> assocType) {
        logger.debug("Running AssociationTypeMaintViewImpl.displayAssociationTypes();");
        new CollectionTableDisplay<AssociationType>(assocType, new AssociationTypeMetaData()).display();
    }
}
