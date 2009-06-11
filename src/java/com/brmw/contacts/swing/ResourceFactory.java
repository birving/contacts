package com.brmw.contacts.swing;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to generate certain swing components from a resource bundle
 */
public class ResourceFactory {
    private static final String DEFAULT_BASE_NAME = "contactsResources";
    private static final Logger logger = LoggerFactory.getLogger(ResourceFactory.class);
    private static final String BUTTON_PREFIX = "button.";
    private static final String MENU_PREFIX = "menu.";

    private static final String ACCEL_SUFFIX = ".accelerator";
    private static final String ICON_SUFFIX = ".icon";
    private static final String MNEMONIC_SUFFIX = ".mnemonic";
    private static final String TOOL_TIP_SUFFIX = ".tooltip";

    private static ResourceFactory instance = new ResourceFactory();

    public static ResourceFactory getInstance() {
        return instance;
    }

    public static void setInstance(ResourceFactory instance) {
        ResourceFactory.instance = instance;
    }

    private ResourceBundle resources;
    private Set<String> keySet;

    public ResourceFactory() {
        this(DEFAULT_BASE_NAME);
    }

    public ResourceFactory(String baseName) {
        resources = ResourceBundle.getBundle(baseName);
        keySet = resources.keySet();
    }

    public Locale getLocaleInUse() {
        logger.debug("Default locale: {}; Effective locale: {}", Locale.getDefault(), resources.getLocale());
        return resources.getLocale();
    }

    public void setCurrentLocale() {
        resources = ResourceBundle.getBundle(DEFAULT_BASE_NAME);
        logger.debug("New default locale: {}; Effective locale: {}", Locale.getDefault(), resources.getLocale());
    }

    public String getString(String key) {
        return getString(key, null, true);
    }

    public String getString(String key, String defaultValue) {
        return getString(key, defaultValue, false);
    }

    private String getString(String key, String defaultValue, boolean required) {
        if (keySet.contains(key)) {
            return resources.getString(key);
        } else if (!required) {
            return defaultValue;
        } else {
            // TODO: Replace with custom Exception
            throw new RuntimeException("Missing resource: " + key);
        }
    }

    public JButton createButton(String key) {
        JButton button = new JButton();
        return (JButton) initializeButton(button, BUTTON_PREFIX + key);
    }

    public JMenu createMenu(String key) {
        JMenu menu = new JMenu();
        return (JMenu) initializeMenuItem(menu, MENU_PREFIX + key);
    }

    public JMenuItem createMenuItem(String key) {
        JMenuItem menuItem = new JMenuItem();
        return initializeMenuItem(menuItem, MENU_PREFIX + key);
    }

    public JCheckBoxMenuItem createCheckBoxMenuItem(String key) {
        JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem();
        return (JCheckBoxMenuItem) initializeMenuItem(menuItem, MENU_PREFIX + key);
    }

    public JRadioButtonMenuItem createRadioButtonMenuItem(String key) {
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem();
        return (JRadioButtonMenuItem) initializeMenuItem(menuItem, MENU_PREFIX + key);
    }

    private JMenuItem initializeMenuItem(JMenuItem menuItem, String key) {
        String accelerator = getString(key + ACCEL_SUFFIX, null);
        if (accelerator != null) {
            KeyStroke keyStroke = KeyStroke.getKeyStroke(accelerator);
            menuItem.setAccelerator(keyStroke);
        }

        return (JMenuItem) initializeButton(menuItem, key);
    }

    private AbstractButton initializeButton(AbstractButton button, String key) {
        String text = getString(key, null);
        if (text == null) {
            return null;
        } else {
            button.setText(text);
        }

        String mnemonic = getString(key + MNEMONIC_SUFFIX, null);
        if (mnemonic != null && mnemonic.length() == 1) {
            button.setMnemonic(mnemonic.codePointAt(0));
        }

        String toolTip = getString(key + TOOL_TIP_SUFFIX, null);
        if (toolTip != null) {
            button.setToolTipText(toolTip);
        }

        String iconPath = getString(key + ICON_SUFFIX, null);
        if (iconPath != null) {
            Icon icon = createImageIcon(iconPath);
            if (icon != null) {
                button.setIcon(icon);
            }
        }

        return button;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ClassLoader.getSystemResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    public List<Image> createImages(String path) {
        List<Image> images = new ArrayList<Image>();

        ImageIcon imageIcon1 = createImageIcon("images/16x16/" + path);
        if (imageIcon1 != null) {
            images.add(imageIcon1.getImage());
        }

        ImageIcon imageIcon2 = createImageIcon("images/22x22/" + path);
        if (imageIcon2 != null) {
            images.add(imageIcon2.getImage());
        }

        ImageIcon imageIcon3 = createImageIcon("images/32x32/" + path);
        if (imageIcon3 != null) {
            images.add(imageIcon3.getImage());
        }

        return images;
    }
}
