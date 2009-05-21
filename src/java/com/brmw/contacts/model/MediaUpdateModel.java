package com.brmw.contacts.model;

import java.util.Collection;

import com.brmw.contacts.domain.Medium;

public interface MediaUpdateModel {
    public Collection<Medium> updateAllMedia(Collection<Medium> media);
}
