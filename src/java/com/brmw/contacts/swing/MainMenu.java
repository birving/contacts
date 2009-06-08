package com.brmw.contacts.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.ContactsState;
import com.brmw.contacts.presenter.PresenterFirstRegistry;

public class MainMenu {
    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private JMenuBar menuBar;
    private Map<Locale, JRadioButtonMenuItem> localeButtonMap = new HashMap<Locale, JRadioButtonMenuItem>();

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

        JMenu toolsMenu = menuBar.add(ResourceFactory.createMenu("Tools"));
        createToolsMenu(toolsMenu);

        JMenu helpMenu = menuBar.add(ResourceFactory.createMenu("Help"));
        createHelpMenu(helpMenu);
    }

    private void createContactsMenu(JMenu contactsMenu) {
        /*
         * Contacts menu
         */
        JMenuItem findPersonMenuItem = contactsMenu.add(ResourceFactory.createMenuItem("Contacts.FindPerson"));
        findPersonMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Find Person not yet implemented.");
            }
        });

        JMenuItem findCompanyMenuItem = contactsMenu.add(ResourceFactory.createMenuItem("Contacts.FindCompany"));
        findCompanyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Find Company not yet implemented.");
            }
        });

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
        JMenu localeMenu = (JMenu) toolsMenu.add(ResourceFactory.createMenu("Tools.Locale"));
        createLocaleMenu(localeMenu);

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
     * Create Locale menu
     */
    private void createLocaleMenu(JMenu localeMenu) {
        logger.debug("Current locale: {}", Locale.getDefault());

        final JRadioButtonMenuItem englishMenuItem = (JRadioButtonMenuItem) localeMenu.add(ResourceFactory
                .createRadioButtonMenuItem("Tools.Locale.English"));
        localeButtonMap.put(Locale.ROOT, englishMenuItem);
        localeButtonMap.put(Locale.ENGLISH, englishMenuItem);
        logger.debug("English locale: {}", Locale.ENGLISH);

        englishMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRestart(Locale.ENGLISH, englishMenuItem);
            }
        });

        final JRadioButtonMenuItem pigLatinMenuItem = (JRadioButtonMenuItem) localeMenu.add(ResourceFactory
                .createRadioButtonMenuItem("Tools.Locale.Piglatin"));
        final Locale piglatinLocale = new Locale("en", "US", "PIGLATIN");
        localeButtonMap.put(piglatinLocale, pigLatinMenuItem);
        logger.debug("Pig Latin locale: {}", piglatinLocale);
        pigLatinMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRestart(piglatinLocale, pigLatinMenuItem);
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(englishMenuItem);
        buttonGroup.add(pigLatinMenuItem);

        ResourceFactory.setCurrentLocale();
        localeButtonMap.get(ResourceFactory.getLocaleInUse()).setSelected(true);
    }

    private void confirmRestart(Locale newLocale, JRadioButtonMenuItem selectedMenuItem) {
        if (selectedMenuItem.equals(localeButtonMap.get(ResourceFactory.getLocaleInUse()))) {
            logger.debug("Locale is not changing");
            return;
        }

        int answer = JOptionPane.showConfirmDialog(null, ResourceFactory.getString("app.confirmRestart"), ResourceFactory
                .getString("app.confirmResart.title"), JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            logger.debug("Setting locale to {}", newLocale);
            Locale.setDefault(newLocale);
            logger.debug("Locale now set to {}", Locale.getDefault());
            ResourceFactory.setCurrentLocale();
            ResourceFactory.getLocaleInUse();
            Contacts.restartContacts();
        }
        selectedMenuItem = localeButtonMap.get(ResourceFactory.getLocaleInUse());
        if (selectedMenuItem == null) {
            selectedMenuItem = localeButtonMap.get(Locale.ROOT);
        }
        selectedMenuItem.setSelected(true);
    }

    /**
     * Create Help menu
     */
    private void createHelpMenu(JMenu helpMenu) {
        JMenuItem helpContentsMenuItem = helpMenu.add(ResourceFactory.createMenuItem("Help.Contents"));
        helpContentsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Help - not yet implemented.");
            }
        });

        JMenuItem aboutMenuItem = helpMenu.add(ResourceFactory.createMenuItem("Help.About"));
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "About - not yet implemented.");
            }
        });
    }
}
