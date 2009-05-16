package com.brmw.contacts.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.model.impl.MediaMaintAction;
import com.brmw.contacts.presenter.MediaMaintPresenter;

public class MainMenu {
    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private JMenuBar menuBar;

    public MainMenu() {
        menuBar = new JMenuBar();
        initialize();
    }

    private void initialize() {
        /*
         * Main application menu
         */
        JMenu contactsMenu = menuBar.add(new JMenu("Contacts"));
        contactsMenu.setMnemonic(KeyEvent.VK_C);

        JMenu toolsMenu = menuBar.add(new JMenu("Tools"));
        toolsMenu.setMnemonic(KeyEvent.VK_T);

        JMenu helpMenu = menuBar.add(new JMenu("Help"));
        helpMenu.setMnemonic(KeyEvent.VK_H);

        /*
         * Contacts menu
         */
        JMenuItem findPersonMenuItem = contactsMenu.add(new JMenuItem("Find person"));
        findPersonMenuItem.setMnemonic(KeyEvent.VK_P);

        JMenuItem findCompanyMenuItem = contactsMenu.add(new JMenuItem("Find company"));
        findCompanyMenuItem.setMnemonic(KeyEvent.VK_C);

        contactsMenu.addSeparator();

        JMenuItem exitMenuItem = contactsMenu.add(new JMenuItem("Exit"));
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Application terminating by menu selection");
                System.exit(0);
            }
        });

        /*
         * Tools menu
         */
        JMenuItem defineMediaMenuItem = toolsMenu.add(new JMenuItem("Define media"));
        defineMediaMenuItem.setMnemonic(KeyEvent.VK_M);
//        new MediaMaintPresenter(defineMediaMenuItem, new MediaMaintAction());
        
        JMenuItem defineAssociationsMenuItem = toolsMenu.add(new JMenuItem("Define associations"));
        defineAssociationsMenuItem.setMnemonic(KeyEvent.VK_A);

        /*
         * Help menu
         */
        JMenuItem helpContentsMenuItem = helpMenu.add(new JMenuItem("Help contents"));
        helpContentsMenuItem.setMnemonic(KeyEvent.VK_H);

        JMenuItem aboutMenuItem = helpMenu.add(new JMenuItem("About Contacts application"));
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
