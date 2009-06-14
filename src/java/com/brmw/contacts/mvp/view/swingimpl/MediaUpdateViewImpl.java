package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.domain.adaptor.MediumMetaData;
import com.brmw.contacts.mvp.view.MediaUpdateView;
import com.brmw.contacts.swing.CollectionTableDisplay;
import com.brmw.contacts.swing.ComponentRegistry;

public class MediaUpdateViewImpl extends AbstractButtonView implements MediaUpdateView {
    private static final Logger logger = LoggerFactory.getLogger(MediaUpdateViewImpl.class);

    public MediaUpdateViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaUpdateViewImpl.displayMedia();");

        @SuppressWarnings("unchecked")
        CollectionTableDisplay<Medium> mediaMaintDisplay =
                (CollectionTableDisplay<Medium>) ComponentRegistry.getInstance()
                        .getCollectionTable(MediumMetaData.REGISTRY_KEY);
        if (mediaMaintDisplay != null) {
            mediaMaintDisplay.setTableData(media);
        } else {
            logger.info("No displayed media table found. Ignoring request.");
        }
    }

    @Override
    public Collection<Medium> getMedia() {
        @SuppressWarnings("unchecked")
        CollectionTableDisplay<Medium> mediaMaintDisplay =
                (CollectionTableDisplay<Medium>) ComponentRegistry.getInstance()
                        .getCollectionTable(MediumMetaData.REGISTRY_KEY);
        if (mediaMaintDisplay != null) {
            return mediaMaintDisplay.getTableData();
        } else {
            throw new IllegalStateException("No media table found!");
        }
    }
}