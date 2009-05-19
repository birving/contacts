package com.brmw.contacts.swing;

import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.table.TableModel;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.util.MediumMetaData;
import com.brmw.contacts.view.MediaMaintView;
/**
 * Media Maintenance GUI elements. 
 * @author bruce
 *
 */
public class MediaMaintButton implements MediaMaintView {
    
    private AbstractButton allMediaButton;
    
    
    protected MediaMaintButton(AbstractButton allMediaButton) {
        this.allMediaButton = allMediaButton;
    }

    public MediaMaintButton() {
        allMediaButton = new JButton("Display all media types");
    }

    public JComponent getComponent() {
        return allMediaButton;
    }

    @Override
    public void addAllMediaRequestListener(ActionListener actionListener) {
        allMediaButton.addActionListener(actionListener);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        
    }

    
}
