package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main GUI screen for Contacts application.
 * 
 * @author Bruce Irving
 * 
 */
public class MainWindow {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 400;

    private JFrame frame;
    private ResourceFactory resourceFactory = ResourceFactory.getInstance();

    public MainWindow() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();

        // Initialization
        frame = new JFrame(resourceFactory.getString("app.title"));
        frame.setIconImages(resourceFactory.createImages("apps/system-users.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(new MainMenu().getMenuBar());

        // Create North (Toolbar?) panel and save reference
        JPanel northPanel = new JPanel();
        frame.add(northPanel, BorderLayout.SOUTH);
        componentRegistry.register("NorthPanel", northPanel);

        // Create Center panel and save reference
        JPanel centerPanel = new JPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
        componentRegistry.register("CenterPanel", centerPanel);

        // Create South (Status) panel and save reference
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.add(southPanel, BorderLayout.SOUTH);
        // componentRegistry.register("SouthPanel", southPanel);

        JLabel statusField = new JLabel(); // "Status: ");
        southPanel.add(statusField);
        componentRegistry.register("StatusField", statusField);
    }

    public void show() {
        // Show the UI.
        frame.pack();
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setVisible(true);
    }

    /**
     * Any required cleanup of the GUI before a restart should go here.
     */
    public void dispose() {
        frame.dispose();
    }
}
