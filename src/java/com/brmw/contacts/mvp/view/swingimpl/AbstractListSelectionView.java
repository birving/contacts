package com.brmw.contacts.mvp.view.swingimpl;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import com.brmw.contacts.mvp.view.ListSelectionView;
import com.brmw.contacts.swing.SelectableList;

/**
 * Base class for View classes in Model-Presenter-View pattern.
 * 
 * This should be extended for any view classes representing a
 * ListSelectionModel (usually in a JTable) which invokes an action.
 * 
 * TODO: Determine if there are classes which need to implement both ButtonView
 * and ListSelectionView. If so, then the two corresponding abstract base
 * classes probably should be combined into one AbstractView.
 * @param <T>
 */
public abstract class AbstractListSelectionView<T> implements ListSelectionView<T> {

    private ListSelectionModel listSelectionModel;
    private SelectableList<T> selectableList;

    protected AbstractListSelectionView(ListSelectionModel listSelectionModel, SelectableList<T> selectableList) {
        this.listSelectionModel = listSelectionModel;
        this.selectableList = selectableList;
    }

    public ListSelectionModel getListSelectionModel() {
        return listSelectionModel;
    }

    @Override
    public void addListSelectionListener(ListSelectionListener listSelectionListener) {
        listSelectionModel.addListSelectionListener(listSelectionListener);
    }

    @Override
    public T getSelected() {
        return selectableList.getSelected(listSelectionModel.getLeadSelectionIndex());
    }
    
    
}
