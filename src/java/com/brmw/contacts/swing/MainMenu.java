package com.brmw.contacts.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.presenter.PresenterFirstRegistry;

public class MainMenu {
    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private JMenuBar menuBar;

    public MainMenu() {
        menuBar = new JMenuBar();
        crateMainMenu();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Create Main application menu
     */
    private void crateMainMenu() {
        JMenu contactsMenu = menuBar.add(ResourceFactory.createMenu("Contacts"));
        createContactsMenu(contactsMenu);
//        JOptionPane.showMessageDialog(null, message, title, messageType)

        JMenu toolsMenu = menuBar.add(ResourceFactory.createMenu("Tools"));
        // toolsMenu.setMnemonic(KeyEvent.VK_T);
        createToolsMenu(toolsMenu);

        JMenu helpMenu = menuBar.add(ResourceFactory.createMenu("Help"));
        // helpMenu.setMnemonic(KeyEvent.VK_H);
        createHelpMenu(helpMenu);
    }

    private void createContactsMenu(JMenu contactsMenu) {
        /*
         * Contacts menu
         */
        @SuppressWarnings("unused")
        JMenuItem findPersonMenuItem = contactsMenu.add(ResourceFactory.createMenuItem("Contacts.FindPerson"));
        
        @SuppressWarnings("unused")
        JMenuItem findCompanyMenuItem = contactsMenu.add(ResourceFactory.createMenuItem("Contacts.FindCompany"));

        contactsMenu.addSeparator();

        JMenuItem exitMenuItem = contactsMenu.add(ResourceFactory.createMenuItem("Contacts.Exit"));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Application terminating by menu selection");
                System.exit(0);
            }
        });
    }

    /**
     * Create Tools menu
     */
    private void createToolsMenu(JMenu toolsMenu) {
        JMenuItem defineMediaMenuItem = toolsMenu.add(ResourceFactory.createMenuItem("Tools.DefineMedia"));
        PresenterFirstRegistry.getInstance().registerMediaMaintButton(defineMediaMenuItem);

        JMenuItem defineAssociationsMenuItem = toolsMenu.add(ResourceFactory.createMenuItem("Tools.DefineAssoc"));
        PresenterFirstRegistry.getInstance().registerAssociationMaintButton(defineAssociationsMenuItem);

        toolsMenu.addSeparator();

        final JMenuItem debugModeMenuItem = toolsMenu.add(ResourceFactory.createCheckBoxMenuItem("Tools.Debug"));
        debugModeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactsState.setDebugMode(debugModeMenuItem.isSelected());
            }
        });
    }

    /**
     * Create Help menu
     */
    private void createHelpMenu(JMenu helpMenu) {
        @SuppressWarnings("unused")
        JMenuItem helpContentsMenuItem = helpMenu.add(ResourceFactory.createMenuItem("Help.Contents"));

        @SuppressWarnings("unused")
        JMenuItem aboutMenuItem = helpMenu.add(ResourceFactory.createMenuItem("Help.About"));
    }
}
