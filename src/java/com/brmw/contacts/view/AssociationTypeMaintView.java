package com.brmw.contacts.view;

import java.util.Collection;

import com.brmw.contacts.domain.AssociationType;

public interface AssociationTypeMaintView extends ButtonView {

    public void displayAssociationTypes(Collection<AssociationType> media);
}
