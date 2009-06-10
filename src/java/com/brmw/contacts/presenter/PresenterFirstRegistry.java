package com.brmw.contacts.presenter;

import javax.swing.AbstractButton;

import com.brmw.contacts.model.impl.AssociationTypeMaintModelImpl;
import com.brmw.contacts.model.impl.MediaMaintModelImpl;
import com.brmw.contacts.model.impl.MediaUpdateModelImpl;
import com.brmw.contacts.swing.impl.AssociationTypeMaintViewImpl;
import com.brmw.contacts.swing.impl.MediaMaintViewImpl;
import com.brmw.contacts.swing.impl.MediaUpdateViewImpl;

/**
 * This class wires together the Model-View-Presenter triads. The code in the
 * class could be (and probably should be) replaced with dependency injection,
 * e.g. via Spring. This class *should be* the only class that knows about the
 * concrete classes on both the view and model side of the application.
 * 
 * Each registerXxx() methods in this class takes one (or more?) Swing
 * components as a parameter. It should always be the most generic component
 * class that satisfies the required API. For example use AbstractButton for any
 * usage that uses an ActionListner (button, checkbox, menu item, etc).
 * 
 * @author bruce
 * 
 */
public class PresenterFirstRegistry {
    private static PresenterFirstRegistry instance = new PresenterFirstRegistry();

    private PresenterFirstRegistry() {
    }

    public static PresenterFirstRegistry getInstance() {
        return instance;
    }

    /**
     * Create MVP triad for media maintenance button (JButton or JMenuItem)
     */
    public void registerMediaMaintButton(AbstractButton button) {
        new MediaMaintPresenter(new MediaMaintViewImpl(button), new MediaMaintModelImpl());
    }

    /**
     * Create MVP triad for media update button (JButton or JMenuItem)
     */
    public void registerMediaUpdateButton(AbstractButton button) {
        new MediaUpdatePresenter(new MediaUpdateViewImpl(button), new MediaUpdateModelImpl());
    }

    /**
     * Create MVP triad for association type maintenance button (JButton or
     * JMenuItem)
     */
    public void registerAssociationTypeMaintButton(AbstractButton button) {
        new AssociationTypeMaintPresenter(new AssociationTypeMaintViewImpl(button), new AssociationTypeMaintModelImpl());
    }
}
