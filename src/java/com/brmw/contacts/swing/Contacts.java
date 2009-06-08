/**
 * 
 */
package com.brmw.contacts.swing;

import java.util.Locale;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.hibernate.HibernateFactory;
import com.brmw.contacts.util.ShutdownHook;

/**
 * Top-level class to invoke Swing interface to Contacts application.
 * 
 * @author Bruce Irving
 * 
 */
public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);
    private static Contacts instance;
    private MainWindow window;
    private Thread shutdownHook;

    /**
     * 
     * @param args
     *            Currently not used.
     */
    Contacts() {
        // Initialize model
        initializeModel();

        // Initialize look and feel
        initializeLookAndFeel();

        shutdownHook = new Thread(new ShutdownHook(), "Shutdown");
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        window = new MainWindow();
    }

    private void initializeModel() {
        HibernateFactory.buildSessionFactory();
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
        logger.debug("Default Locale: {}", Locale.getDefault());
        startContacts();
    }

    private static void startContacts() {
        instance = new Contacts();

        // Launch GUI on the event-dispatching thread.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                instance.show();
            }
        });
    }

    public static void restartContacts() {
        logger.debug("Default Locale: {}", Locale.getDefault());

        // Remove existing GUI components
        instance.window.dispose();

        ComponentRegistry.getInstance().clear();
        Runtime.getRuntime().removeShutdownHook(instance.shutdownHook);
        instance = null;

        startContacts();
    }
}
