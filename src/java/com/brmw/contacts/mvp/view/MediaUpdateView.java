package com.brmw.contacts.mvp.view;

import java.awt.event.ActionListener;
import java.util.Collection;

import com.brmw.contacts.domain.Medium;

public interface MediaUpdateView {

    public void addActionListener(ActionListener actionListener);

    public void displayMedia(Collection<Medium> media);

    public Collection<Medium> getMedia();
}
