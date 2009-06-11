package com.brmw.contacts.view;

import java.awt.event.ActionListener;
import java.util.Collection;

import com.brmw.contacts.domain.AssociationType;

public interface AssociationTypeUpdateView {

    public void addActionListener(ActionListener actionListener);

    public void displayAssociationTypes(Collection<AssociationType> associationTypes);

    public Collection<AssociationType> getAssociationType();
}
