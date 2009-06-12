package com.brmw.contacts.mvp.view.swingimpl;

import java.util.Collection;

import javax.swing.AbstractButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.mvp.PresenterFirstRegistry;
import com.brmw.contacts.mvp.view.MediaMaintView;
import com.brmw.contacts.swing.CollectionTableDisplay;
import com.brmw.contacts.util.MediumMetaData;

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
        logger.debug("Running MediaMaintViewImpl.displayMedia();");
        CollectionTableDisplay<Medium> mediaDisplay = new CollectionTableDisplay<Medium>(media, new MediumMetaData()) {
            @Override
            protected void registerSaveButton(AbstractButton button) {
                PresenterFirstRegistry.getInstance().registerMediaUpdateButton(button);
            }

            @Override
            protected void registerRevertButton(AbstractButton button) {
                PresenterFirstRegistry.getInstance().registerMediaMaintButton(button);
            }
        };
        mediaDisplay.display();
    }
}
