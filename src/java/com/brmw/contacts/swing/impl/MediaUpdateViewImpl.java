package com.brmw.contacts.swing.impl;

import java.awt.Container;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.swing.CollectionTableModel;
import com.brmw.contacts.swing.ComponentRegistry;
import com.brmw.contacts.swing.MediaMaintDisplay;
import com.brmw.contacts.util.MediumMetaData;
import com.brmw.contacts.view.MediaUpdateView;

public class MediaUpdateViewImpl extends AbstractButtonView implements MediaUpdateView {
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdateViewImpl.class);
    
    public MediaUpdateViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaUpdateViewImpl.displayMedia();" );
        TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        MediaMaintDisplay.displayTableInCenterPanel("Define media", mediaTableModel);

//        Container container = (Container) ComponentRegistry.getInstance().getComponent("CenterPanel");
//        JTable mediaTable = (JTable) ComponentRegistry.getInstance().getComponent(MediaMaintDisplay.MEDIA_TABLE);
//        if (mediaTable != null) {
//            TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
//            mediaTable.setModel(mediaTableModel);
//        } else {
//            throw new IllegalStateException("No table model found!");
//        }
//        container.setVisible(false);
//        container.validate();
//        container.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Medium> getMedia() {
        JTable mediaTable = (JTable) ComponentRegistry.getInstance().getComponent(MediaMaintDisplay.MEDIA_TABLE);
        if (mediaTable != null) {
            return ((CollectionTableModel<Medium>)mediaTable.getModel()).getTableData();
        } else {
            throw new IllegalStateException("No table model found!");
        }
    }

}
