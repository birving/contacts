package com.brmw.contacts.swing;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

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

import com.brmw.contacts.ResourceFactory;

/**
 * Utility class to generate certain swing components from a resource bundle
 */
public class SwingResourceFactory extends ResourceFactory {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SwingResourceFactory.class);

    private static final String BUTTON_PREFIX = "button.";
    private static final String MENU_PREFIX = "menu.";
    private static final String ACCEL_SUFFIX = ".accelerator";
    private static final String ICON_SUFFIX = ".icon";
    private static final String MNEMONIC_SUFFIX = ".mnemonic";
    private static final String TEXT_SUFFIX = ".text";
    private static final String TOOL_TIP_SUFFIX = ".tooltip";
    private static SwingResourceFactory instance = new SwingResourceFactory();

    public static SwingResourceFactory getInstance() {
        return instance;
    }

    public static void setInstance(SwingResourceFactory instance) {
        SwingResourceFactory.instance = instance;
    }

    /**
     * Default constructor
     */
    public SwingResourceFactory() {
        super();
    }

    /**
     * Constructor for test use
     */
    public SwingResourceFactory(String baseName) {
        super(baseName);
    }

    // Create Swing components using strings from resource bundle

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

    public Icon createIcon(String key) {
        String iconPath = getString(key + ICON_SUFFIX, null);
        if (iconPath != null) {
            return loadImageIcon(iconPath);
        } else {
            return null;
        }
    }

    public List<Image> createImages(String key) {
        String iconPath = getString(key, null);
        if (iconPath != null) {
            return loadImages(iconPath);
        } else {
            return null;
        }
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
        String text = getString(key + TEXT_SUFFIX, null);
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
            Icon icon = loadImageIcon(iconPath);
            if (icon != null) {
                button.setIcon(icon);
            }
        }

        return button;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon loadImageIcon(String path) {
        java.net.URL imgURL = ClassLoader.getSystemResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    private List<Image> loadImages(String path) {
        List<Image> images = new ArrayList<Image>();

        ImageIcon imageIcon1 = loadImageIcon("images/16x16/" + path);
        if (imageIcon1 != null) {
            images.add(imageIcon1.getImage());
        }

        ImageIcon imageIcon2 = loadImageIcon("images/22x22/" + path);
        if (imageIcon2 != null) {
            images.add(imageIcon2.getImage());
        }

        ImageIcon imageIcon3 = loadImageIcon("images/32x32/" + path);
        if (imageIcon3 != null) {
            images.add(imageIcon3.getImage());
        }

        return images;
    }
}
