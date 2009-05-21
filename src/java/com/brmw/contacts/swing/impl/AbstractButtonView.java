package com.brmw.contacts.swing.impl;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;

import com.brmw.contacts.view.ButtonView;

public abstract class AbstractButtonView implements ButtonView {

    private AbstractButton button;

    protected AbstractButtonView(AbstractButton mediaMaintButton) {
        this.button = mediaMaintButton;
    }

    public JComponent getComponent() {
        return button;
    }

    public void addActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
    }
}
