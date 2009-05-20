package com.brmw.contacts.swing;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.util.MediumMetaData;
import com.brmw.contacts.view.MediaMaintView;

/**
 * Media Maintenance GUI elements.
 * 
 * @author bruce
 * 
 */
public class MediaMaintViewImpl implements MediaMaintView {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintViewImpl.class);
    private AbstractButton mediaMaintButton;

    protected MediaMaintViewImpl(AbstractButton mediaMaintButton) {
        this.mediaMaintButton = mediaMaintButton;
    }

    public JComponent getComponent() {
        return mediaMaintButton;
    }

    @Override
    public void addMediaMaintRequestListener(ActionListener actionListener) {
        mediaMaintButton.addActionListener(actionListener);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaMaintViewImpl.displayMedia();" );
        TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        SwingHelper.displayTableInCenterPanel("Define media", mediaTableModel);
    }

}
