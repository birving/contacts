package com.brmw.contacts.swing.impl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.swing.ComponentRegistry;
import com.brmw.contacts.swing.MediaMaintDisplay;
import com.brmw.contacts.view.MediaUpdateView;

public class MediaUpdateViewImpl extends AbstractButtonView implements MediaUpdateView {
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdateViewImpl.class);

    public MediaUpdateViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaUpdateViewImpl.displayMedia();");

        MediaMaintDisplay mediaMaintDisplay = (MediaMaintDisplay) ComponentRegistry.getInstance().getComponent(MediaMaintDisplay.MEDIA_MAINT);
        if (mediaMaintDisplay != null) {
            mediaMaintDisplay.setTableData(media);
        } else {
            logger.info("No displayed media table found. Ignoring request.");
        }
    }

    @Override
    public Collection<Medium> getMedia() {
        MediaMaintDisplay mediaMaintDisplay = (MediaMaintDisplay) ComponentRegistry.getInstance().getComponent(MediaMaintDisplay.MEDIA_MAINT);
        if (mediaMaintDisplay != null) {
            return mediaMaintDisplay.getTableData();
        } else {
            throw new IllegalStateException("No media table found!");
        }
    }
}
