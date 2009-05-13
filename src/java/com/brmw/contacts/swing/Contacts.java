/**
 * 
 */
package com.brmw.contacts.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

    private JFrame frame;

    /**
     * 
     * @param args
     *            Currently not used.
     */
    Contacts(String[] args) {
        // Initialize look and feel
        initializeLookAndFeel();

        // Other initialization
        frame = new JFrame("Contacts Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Contacts Management");
        frame.getContentPane().add(label);
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
        // Show the UI.
        frame.pack();
        frame.setVisible(true);
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
