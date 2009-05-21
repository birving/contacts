package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main GUI screen for Contacts application.
 * @author Bruce Irving
 *
 */
public class MainWindow {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 400;

    private JFrame frame;

    public MainWindow() {
        PresenterFirstSwingRegistry registry = PresenterFirstSwingRegistry.getInstance();
        
        // Initialization
        frame = new JFrame("Contacts Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(new MainMenu().getMenuBar());

        JLabel label = new JLabel("Contacts Management - This label has to go!!");
        Container contentPane = frame.getContentPane();
        contentPane.add(label, BorderLayout.NORTH);
        
        // Create Center panel and save reference
        JPanel centerPanel = new JPanel();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        ComponentRegistry.getInstance().register("CenterPanel", centerPanel);

        // Create South (Status) panel and save reference 
        JPanel southPanel = new JPanel();
        contentPane.add(label, BorderLayout.SOUTH);
        ComponentRegistry.getInstance().register("SouthPanel", southPanel);

        JButton mediaMaintButton = new JButton("Display all media types");
        contentPane.add(mediaMaintButton, BorderLayout.SOUTH);
        registry.registerMediaMaintButton(mediaMaintButton);
    }

    public void show() {
         // Show the UI.
         frame.pack();
         frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
         frame.setVisible(true);
    }
}
