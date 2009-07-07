package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    private static final int MIN_WIDTH = DEFAULT_WIDTH;
    private static final int MIN_HEIGHT = DEFAULT_HEIGHT;

    private JFrame frame;
    private SwingResourceFactory resourceFactory = SwingResourceFactory.getInstance();

    public MainWindow() {
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();

        // Initialization
        frame = new JFrame(resourceFactory.getString("app.title"));
        frame.setIconImages(resourceFactory.createImages("app.images"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(new MainMenu().getMenuBar());

        // Create top (Toolbar?) panel and save reference
        JPanel pageStartPanel = new JPanel();
        frame.add(pageStartPanel, BorderLayout.PAGE_START);
        componentRegistry.register("PageStartPanel", pageStartPanel);

        // Create Center panel and save reference
        JPanel centerPanel = new JPanel();
        frame.add(centerPanel, BorderLayout.CENTER);
        componentRegistry.register("CenterPanel", centerPanel);

        // Create bottom (Status) panel and save reference
        JPanel pageEndPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        frame.add(pageEndPanel, BorderLayout.PAGE_END);
        // componentRegistry.register("PageEndPanel", pageEndPanel);

        JLabel statusField = new JLabel(); // "Status: ");
        pageEndPanel.add(statusField);
        componentRegistry.register("StatusField", statusField);
    }

    public void show() {
        // Show the UI.
        frame.pack();
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        frame.setVisible(true);
    }

    /**
     * Any required cleanup of the GUI before a restart should go here.
     */
    public void dispose() {
        frame.dispose();
    }
}
