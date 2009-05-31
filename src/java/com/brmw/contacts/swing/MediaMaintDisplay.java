package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.presenter.PresenterFirstRegistry;
import com.brmw.contacts.util.MediumMetaData;

/**
 * This class builds the display for the Media Maintenance function.
 * 
 * @author bruce
 * 
 */
public class MediaMaintDisplay {
    public static final String MEDIA_MAINT = "MediaMaint";

    private Container container;
    private CollectionTableModel<Medium> collectionTableModel;
    private String tableHeading = "Define media";
    private JButton saveButton;

    public MediaMaintDisplay(Collection<Medium> media) {
        this.collectionTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        ComponentRegistry.getInstance().register(MEDIA_MAINT, this);
    }

    /**
     * Update Medium data in table
     * 
     * @param media
     *            the full Medium collection
     */
    public void setTableData(Collection<Medium> media) {
        collectionTableModel.setTableData(media);
        saveButton.setEnabled(false);
    }

    public Collection<Medium> getTableData() {
        return collectionTableModel.getTableData();
    }

    public void display() {

        this.container = (Container) ComponentRegistry.getInstance().getComponent("CenterPanel");
        container.removeAll();
        container.setLayout(new BorderLayout());

        JLabel tableLabel = new JLabel(tableHeading, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText("What is this table used for");
        container.add(tableLabel, BorderLayout.NORTH);

        JTable table = new JTable(collectionTableModel);
        // ComponentRegistry.getInstance().register(MEDIA_TABLE, table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add entry");
        buttonPanel.add(addButton);
        addButton.setMnemonic(KeyEvent.VK_A);

        saveButton = new JButton("Save changes");
        buttonPanel.add(saveButton);
        saveButton.setMnemonic(KeyEvent.VK_S);

        saveButton.setEnabled(false);
        collectionTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                saveButton.setEnabled(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
            }
        });
        PresenterFirstRegistry.getInstance().registerMediaUpdateButton(saveButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        container.validate();
        container.setVisible(true);
    }
}
