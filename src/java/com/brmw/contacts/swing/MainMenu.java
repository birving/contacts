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
import com.brmw.contacts.domain.Person;
import com.brmw.contacts.mvp.PresenterFirstRegistry;

public class MainMenu {
    private static final Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private JMenuBar menuBar;
    private Map<Locale, JRadioButtonMenuItem> localeButtonMap = new HashMap<Locale, JRadioButtonMenuItem>();
    private SwingResourceFactory resourceFactory = SwingResourceFactory.getInstance();

    public MainMenu() {
        menuBar = new JMenuBar();
        createMainMenu();
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Create Main application menu
     */
    private void createMainMenu() {
        JMenu contactsMenu = menuBar.add(resourceFactory.createMenu("Contacts"));
        createContactsMenu(contactsMenu);

        JMenu toolsMenu = menuBar.add(resourceFactory.createMenu("Tools"));
        createToolsMenu(toolsMenu);

        JMenu helpMenu = menuBar.add(resourceFactory.createMenu("Help"));
        createHelpMenu(helpMenu);
    }

    private void createContactsMenu(JMenu contactsMenu) {
        /*
         * Contacts menu
         */
        JMenuItem findPersonMenuItem = contactsMenu.add(resourceFactory.createMenuItem("Contacts.FindPerson"));
        PresenterFirstRegistry.getInstance().registerPersonListButton(findPersonMenuItem);
        // findPersonMenuItem.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // JOptionPane.showMessageDialog(
        // null,
        // "Find Person by search not yet implemented. So far its just 'get all persons'.");
        // }
        // });

        JMenuItem newPersonMenuItem = contactsMenu.add(resourceFactory.createMenuItem("Contacts.NewPerson"));
        newPersonMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.debug("Creating new Person and opening in PersonDetail view");
                PersonDetail personDetail = new PersonDetail();
                personDetail.setPerson(new Person());
                personDetail.display();
            }
        });

        JMenuItem findCompanyMenuItem = contactsMenu.add(resourceFactory.createMenuItem("Contacts.FindCompany"));
        // PresenterFirstRegistry.getInstance().registerCompanyMaintButton(findCompanyMenuItem);
        findCompanyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Find Company not yet implemented.");
            }
        });

        contactsMenu.addSeparator();

        JMenuItem exitMenuItem = contactsMenu.add(resourceFactory.createMenuItem("Contacts.Exit"));
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
        JMenu localeMenu = (JMenu) toolsMenu.add(resourceFactory.createMenu("Tools.Locale"));
        createLocaleMenu(localeMenu);

        JMenuItem defineMediaMenuItem = toolsMenu.add(resourceFactory.createMenuItem("Tools.DefineMedia"));
        PresenterFirstRegistry.getInstance().registerMediaMaintButton(defineMediaMenuItem);

        JMenuItem defineAssociationsMenuItem = toolsMenu.add(resourceFactory.createMenuItem("Tools.DefineAssoc"));
        PresenterFirstRegistry.getInstance().registerAssociationTypeMaintButton(defineAssociationsMenuItem);

        toolsMenu.addSeparator();

        final JMenuItem debugModeMenuItem = toolsMenu.add(resourceFactory.createCheckBoxMenuItem("Tools.Debug"));
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

        final JRadioButtonMenuItem englishMenuItem =
                (JRadioButtonMenuItem) localeMenu.add(resourceFactory.createRadioButtonMenuItem("Tools.Locale.English"));
        localeButtonMap.put(Locale.ROOT, englishMenuItem);
        localeButtonMap.put(Locale.ENGLISH, englishMenuItem);
        logger.debug("English locale: {}", Locale.ENGLISH);

        englishMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRestart(Locale.ENGLISH, englishMenuItem);
            }
        });

        final JRadioButtonMenuItem pigLatinMenuItem =
                (JRadioButtonMenuItem) localeMenu.add(resourceFactory.createRadioButtonMenuItem("Tools.Locale.Piglatin"));
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

        resourceFactory.setCurrentLocale();
        localeButtonMap.get(resourceFactory.getLocaleInUse()).setSelected(true);
    }

    private void confirmRestart(Locale newLocale, JRadioButtonMenuItem selectedMenuItem) {
        if (selectedMenuItem.equals(localeButtonMap.get(resourceFactory.getLocaleInUse()))) {
            logger.debug("Locale is not changing");
            return;
        }

        int answer =
                JOptionPane.showConfirmDialog(
                                              null, resourceFactory.getString("app.confirmRestart.text", null),
                                              resourceFactory.getString("app.confirmRestart.title"),
                                              JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            logger.debug("Setting locale to {}", newLocale);
            Locale.setDefault(newLocale);
            logger.debug("Locale now set to {}", Locale.getDefault());
            resourceFactory.setCurrentLocale();
            resourceFactory.getLocaleInUse();
            Contacts.restartContacts();
        }
        selectedMenuItem = localeButtonMap.get(resourceFactory.getLocaleInUse());
        if (selectedMenuItem == null) {
            selectedMenuItem = localeButtonMap.get(Locale.ROOT);
        }
        selectedMenuItem.setSelected(true);
    }

    /**
     * Create Help menu
     */
    private void createHelpMenu(JMenu helpMenu) {
        JMenuItem helpContentsMenuItem = helpMenu.add(resourceFactory.createMenuItem("Help.Contents"));
        helpContentsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Help - not yet implemented.");
            }
        });

        JMenuItem aboutMenuItem = helpMenu.add(resourceFactory.createMenuItem("Help.About"));
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                                              null, resourceFactory.getString("app.about.text") + "\n",
                                              resourceFactory.getString("app.about.title"),
                                              JOptionPane.INFORMATION_MESSAGE, resourceFactory.createIcon("app.about"));
            }
        });
    }
}
