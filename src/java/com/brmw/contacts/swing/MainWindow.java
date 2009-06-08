package com.brmw.contacts.swing;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.presenter.PresenterFirstRegistry;

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

    public MainWindow() {
        PresenterFirstRegistry presenter1stRegistry = PresenterFirstRegistry.getInstance();
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();

        // Initialization
        frame = new JFrame(ResourceFactory.getString("app.title"));
        componentRegistry.register("MainWindow", frame);
        frame.setIconImages(ResourceFactory.createImages("apps/system-users.png"));
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
        JPanel southPanel = new JPanel();
        frame.add(southPanel, BorderLayout.SOUTH);
        componentRegistry.register("SouthPanel", southPanel);

        JButton mediaMaintButton = ResourceFactory.createButton("Media");
        frame.add(mediaMaintButton, BorderLayout.SOUTH);
        presenter1stRegistry.registerMediaMaintButton(mediaMaintButton);
    }

    public void show() {
        // Show the UI.
        frame.pack();
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setVisible(true);
    }
}
