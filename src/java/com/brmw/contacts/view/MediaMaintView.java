package com.brmw.contacts.view;

import java.awt.event.ActionListener;
import java.util.List;

import com.brmw.contacts.domain.Medium;

public interface MediaMaintView {

    public void addAllMediaRequestListener(ActionListener actionListener);
    
    public void displayMedia(List<Medium> media);

}
