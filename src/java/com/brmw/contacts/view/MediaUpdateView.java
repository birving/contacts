package com.brmw.contacts.view;

import java.awt.event.ActionListener;
import java.util.Collection;

import com.brmw.contacts.domain.Medium;

public interface MediaUpdateView {

    public void addMediaUpdateRequestListener(ActionListener actionListener);
    public void displayMedia(Collection<Medium> media);
    public Collection<Medium> getMedia();

}
