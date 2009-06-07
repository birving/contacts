package com.brmw.contacts.swing.impl;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;

import com.brmw.contacts.view.ButtonView;

/**
 * Base class for View classes in Model-Presenter-View pattern.
 * 
 * This should be extended for any view classes representing button or menu
 * items which invoke an action.
 */
public abstract class AbstractButtonView implements ButtonView {

    private AbstractButton button;

    protected AbstractButtonView(AbstractButton button) {
        this.button = button;
    }

    public JComponent getComponent() {
        return button;
    }

    public void addActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
}
