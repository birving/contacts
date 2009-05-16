package com.brmw.contacts.swing;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.view.MediaMaintView;
/**
 * Media Maintenance GUI elements. 
 * @author bruce
 *
 */
public class MediaMaintButton implements MediaMaintView {
    
    private JButton allMediaButton;
    
    
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
    public void displayMedia(List<Medium> media) {
        // TODO Auto-generated method stub

    }

    
}
