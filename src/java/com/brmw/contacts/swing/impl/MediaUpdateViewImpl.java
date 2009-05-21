package com.brmw.contacts.swing.impl;

import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.JTable;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.swing.CollectionTableModel;
import com.brmw.contacts.swing.ComponentRegistry;
import com.brmw.contacts.swing.MediaMaintDisplay;
import com.brmw.contacts.view.MediaUpdateView;

public class MediaUpdateViewImpl extends AbstractButtonView implements MediaUpdateView {

    public MediaUpdateViewImpl(AbstractButton mediaMaintButton) {
        super(mediaMaintButton);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Medium> getMedia() {
        JTable mediaTable = (JTable) ComponentRegistry.getInstance().getComponent(MediaMaintDisplay.MEDIA_TABLE);
        if (mediaTable != null) {
            return ((CollectionTableModel)mediaTable.getModel()).getTableData();
        } else {
            throw new IllegalStateException("No table model found!");
        }
    }

}
