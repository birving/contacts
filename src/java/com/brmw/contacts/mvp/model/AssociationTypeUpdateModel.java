package com.brmw.contacts.mvp.model;

import java.util.Collection;

import com.brmw.contacts.domain.AssociationType;

public interface AssociationTypeUpdateModel {
    public Collection<AssociationType> updateAllAssociationTypes(Collection<AssociationType> media);
}
