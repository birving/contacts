package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.domain.adaptor.AssociationTypeMetaData;
import com.brmw.contacts.mvp.view.AssociationTypeUpdateView;
import com.brmw.contacts.swing.CollectionTableDisplay;
import com.brmw.contacts.swing.ComponentRegistry;

public class AssociationTypeUpdateViewImpl extends AbstractButtonView implements AssociationTypeUpdateView {
    private static final Logger logger = LoggerFactory.getLogger(AssociationTypeUpdateViewImpl.class);

    public AssociationTypeUpdateViewImpl(AbstractButton associationTypeMaintButton) {
        super(associationTypeMaintButton);
    }

    @Override
    public void displayAssociationTypes(Collection<AssociationType> associationTypes) {
        logger.debug("Running AssociationTypeUpdateViewImpl.displayAssociationType();");

        @SuppressWarnings("unchecked")
        CollectionTableDisplay<AssociationType> associationTypeMaintDisplay =
                (CollectionTableDisplay<AssociationType>) ComponentRegistry.getInstance()
                        .getCollectionTable(AssociationTypeMetaData.REGISTRY_KEY);

        if (associationTypeMaintDisplay != null) {
            associationTypeMaintDisplay.setTableData(associationTypes);
        } else {
            logger.info("No displayed associationType table found. Ignoring request.");
        }
    }

    @Override
    public Collection<AssociationType> getAssociationType() {
        @SuppressWarnings("unchecked")
        CollectionTableDisplay<AssociationType> associationTypeMaintDisplay =
                (CollectionTableDisplay<AssociationType>) ComponentRegistry.getInstance()
                        .getCollectionTable(AssociationTypeMetaData.REGISTRY_KEY);
        if (associationTypeMaintDisplay != null) {
            return associationTypeMaintDisplay.getTableData();
        } else {
            throw new IllegalStateException("No associationType table found!");
        }
    }
}