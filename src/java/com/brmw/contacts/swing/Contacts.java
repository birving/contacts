/**
 * 
 */
package com.brmw.contacts.swing;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Top-level class to invoke Swing interface to Contacts application.
 * 
 * @author Bruce Irving
 * 
 */
public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);
    private MainWindow window;

    /**
     * 
     * @param args
     *            Currently not used.
     */
    Contacts(String[] args) {
        // Initialize look and feel
        initializeLookAndFeel();

        window = new MainWindow();
    }

    private void initializeLookAndFeel() {
        String lookAndFeelClassName = "Unknown";
        try {
            lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeelClassName);
        } catch (ClassNotFoundException e) {
            logger.warn("Unable to set requested look and feel", e);
        } catch (InstantiationException e) {
            logger.warn("Unable to set requested look and feel", e);
        } catch (IllegalAccessException e) {
            logger.warn("Unable to set requested look and feel", e);
        } catch (UnsupportedLookAndFeelException e) {
            logger.warn("Requested look and feel is not supported: {}", lookAndFeelClassName);
        }
        logger.debug("Using: {}", UIManager.getLookAndFeel());
    }

    public void show() {
        window.show();
    }

    /**
     * @param args
     *            Currently not used.
     */
    public static void main(final String[] args) {
        // Launch GUI on the event-dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Contacts(args).show();
            }
        });
    }
}
