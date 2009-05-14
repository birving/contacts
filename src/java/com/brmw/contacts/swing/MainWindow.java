package com.brmw.contacts.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main GUI screen for Contacts application.
 * @author Bruce Irving
 *
 */
public class MainWindow {
    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 400;

    private JFrame frame;
    private MainMenu mainMenu;
    private JMenuBar menuBar;

    public MainWindow() {
        // Initialization
        frame = new JFrame("Contacts Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu = new MainMenu();
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JLabel label = new JLabel("Contacts Management");
        frame.getContentPane().add(label);
    }

    public void show() {
         // Show the UI.
         frame.pack();
         frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
         frame.setVisible(true);
    }
}
