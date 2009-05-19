package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.model.impl.MediaMaintAction;
import com.brmw.contacts.presenter.MediaMaintPresenter;
import com.brmw.contacts.util.MediumMetaData;

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
        PresenterFirstSwingRegistry registry = new PresenterFirstSwingRegistry();
        
        // Initialization
        frame = new JFrame("Contacts Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setJMenuBar(new MainMenu().getMenuBar());

        JLabel label = new JLabel("Contacts Management - This label has to go!!");
        Container contentPane = frame.getContentPane();
        contentPane.add(label, BorderLayout.NORTH);

//        Collection<Medium> tableData = new ArrayList<Medium>();
//        Medium medium1 = new Medium();
//        medium1.setName("Primary email");
//        medium1.setType("email");
//        tableData.add(medium1);
//
//        Medium medium2 = new Medium();
//        medium2.setName("Secondary email");
//        medium2.setType("email");
//        tableData.add(medium2);
//
//        TableModel mediaTableModel = new CollectionTableModel<Medium>(tableData, new MediumMetaData());
//
//        JTable table = new JTable(mediaTableModel);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//        table.setAutoCreateRowSorter(true);
//
//        contentPane.add(scrollPane, BorderLayout.CENTER);

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
