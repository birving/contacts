package com.brmw.contacts.mvp.view;

import java.util.Collection;

import com.brmw.contacts.domain.AssociationType;

public interface AssociationTypeMaintView extends ButtonView {

    public void displayAssociationTypes(Collection<AssociationType> media);
}
