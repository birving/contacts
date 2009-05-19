package com.brmw.contacts.swing;

import javax.swing.AbstractButton;

import com.brmw.contacts.model.impl.MediaMaintAction;
import com.brmw.contacts.presenter.MediaMaintPresenter;

/**
 * This class wires together the Model-View-Presenter triads. The code in the
 * class could be replaced with dependency injection, e.g. via Spring.
 * 
 * Each registerXxx() methods in this class takes one (or more?) Swing
 * components as a parameter. It should always be the most generic component
 * class that satisfies the required API. For example use AbstractButton for any
 * usage that uses an ActionListner (button, checkbox, radio button, menu item).
 * 
 * @author bruce
 * 
 */
public class PresenterFirstSwingRegistry {

    protected PresenterFirstSwingRegistry() {
        assembleTriads();
    }

    public void assembleTriads() {

        // MediaMaintButton mediaMaintButton = new MediaMaintButton();
        // contentPane.add(mediaMaintButton.getComponent(), BorderLayout.SOUTH);
        // new MediaMaintPresenter(mediaMaintButton, new MediaMaintAction());

    }

    /**
     * Create MVP triad for media maintenance button (JButton or JMenuItem)
     * 
     */
    public void registerMediaMaintButton(AbstractButton button) {
        new MediaMaintPresenter(new MediaMaintButton(button), new MediaMaintAction());
    }
}
