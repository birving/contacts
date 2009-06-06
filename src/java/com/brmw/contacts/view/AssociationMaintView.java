package com.brmw.contacts.view;

import java.util.Collection;

import com.brmw.contacts.domain.Association;


public interface AssociationMaintView extends ButtonView {

    public void displayAssociations(Collection<Association> media);
}
