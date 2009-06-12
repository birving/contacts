package com.brmw.contacts.mvp.view;

import java.util.Collection;

import com.brmw.contacts.domain.Medium;

public interface MediaMaintView extends ButtonView {

    public void displayMedia(Collection<Medium> media);
}
