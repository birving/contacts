package com.brmw.contacts.mvp.view;

import javax.swing.event.ListSelectionListener;

public interface ListSelectionView<T> {
    public void addListSelectionListener(ListSelectionListener listSelectionListener);
    
    public T getSelected();
}
