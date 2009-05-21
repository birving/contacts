package com.brmw.contacts.swing.impl;

import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.swing.CollectionTableModel;
import com.brmw.contacts.swing.SwingHelper;
import com.brmw.contacts.util.MediumMetaData;
import com.brmw.contacts.view.MediaMaintView;

/**
 * Media Maintenance GUI elements.
 * 
 */
public class MediaMaintViewImpl extends AbstractButtonView implements MediaMaintView {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintViewImpl.class);

    public MediaMaintViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaMaintViewImpl.displayMedia();" );
        TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        SwingHelper.displayTableInCenterPanel("Define media", mediaTableModel);
    }
}
