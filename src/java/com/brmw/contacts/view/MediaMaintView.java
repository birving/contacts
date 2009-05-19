package com.brmw.contacts.view;

import java.awt.event.ActionListener;
import java.util.Collection;

import com.brmw.contacts.domain.Medium;


public interface MediaMaintView {

    public void addAllMediaRequestListener(ActionListener actionListener);
    
    public void displayMedia(Collection<Medium> media);

}
