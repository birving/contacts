package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.AssociationType;
import com.brmw.contacts.mvp.PresenterFirstRegistry;
import com.brmw.contacts.mvp.view.AssociationTypeMaintView;
import com.brmw.contacts.swing.CollectionTableDisplay;
import com.brmw.contacts.util.AssociationTypeMetaData;

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
    public void displayAssociationTypes(Collection<AssociationType> associationTypes) {
        logger.debug("Running AssociationTypeMaintViewImpl.displayAssociationTypes();");
        CollectionTableDisplay<AssociationType> associationTypeDisplay =
                new CollectionTableDisplay<AssociationType>(associationTypes, new AssociationTypeMetaData()) {
                    @Override
                    protected void registerSaveButton(AbstractButton button) {
                        PresenterFirstRegistry.getInstance().registerAssociationTypeUpdateButton(button);
                    }

                    @Override
                    protected void registerRevertButton(AbstractButton button) {
                        PresenterFirstRegistry.getInstance().registerAssociationTypeMaintButton(button);
                    }
                };
        associationTypeDisplay.display();
    }
}
