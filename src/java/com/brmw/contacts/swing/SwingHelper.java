package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

public class SwingHelper {
    private static Boolean debugMode = Boolean.FALSE;

    public static void displayTableInCenterPanel(String tableHeading, TableModel mediaTableModel) {
        Container container = (Container) ComponentRegistry.getInstance().getComponent("CenterPanel");
        container.removeAll();
        container.setLayout(new BorderLayout());

        JLabel tableLabel = new JLabel(tableHeading, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText("What is this table used for");
        container.add(tableLabel, BorderLayout.NORTH);

        JTable table = new JTable(mediaTableModel);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add entry");
        buttonPanel.add(addButton);
        JButton saveButton = new JButton("Save changes");
        buttonPanel.add(saveButton);
        
        container.add(buttonPanel, BorderLayout.SOUTH);

        container.validate();
        container.setVisible(true);
    }

    public static Boolean getDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(Boolean debugMode) {
        SwingHelper.debugMode = debugMode;
    }

}
