package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
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
