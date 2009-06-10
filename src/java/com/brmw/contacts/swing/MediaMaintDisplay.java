package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableRowSorter;

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

    private CollectionTableModel<Medium> collectionTableModel;
    private String tableHeading = "Define media";
    private JButton saveButton;
    private JButton revertButton;

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
        revertButton.setEnabled(false);
    }

    public Collection<Medium> getTableData() {
        return collectionTableModel.getTableData();
    }

    public void display() {

        Container container = (Container) ComponentRegistry.getInstance().getComponent("CenterPanel");
        container.removeAll();
        container.setLayout(new BorderLayout());

        JLabel tableLabel = new JLabel(tableHeading, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText("What is this table used for");
        container.add(tableLabel, BorderLayout.NORTH);

        final JTable table = new JTable(collectionTableModel);
        table.setFillsViewportHeight(true);
        // table.setAutoCreateRowSorter(true);
        TableRowSorter<CollectionTableModel<Medium>> rowSorter = new TableRowSorter<CollectionTableModel<Medium>>(collectionTableModel);
        rowSorter.setRowFilter(new RowFilter<CollectionTableModel<Medium>, Integer>() {
            @Override
            public boolean include(javax.swing.RowFilter.Entry<? extends CollectionTableModel<Medium>, ? extends Integer> entry) {
                CollectionTableModel<Medium> tableModel = entry.getModel();
                Medium medium = tableModel.getTableData().get(entry.getIdentifier());
                return !medium.isDeleted();
            }
        });
        table.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        container.add(buttonPanel, BorderLayout.SOUTH);
        JPanel leftBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(leftBtnPanel, BorderLayout.WEST);
        JPanel rightBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(rightBtnPanel, BorderLayout.EAST);

        JButton addButton = new JButton("Add entry");
        leftBtnPanel.add(addButton);
        addButton.setMnemonic(KeyEvent.VK_A);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectionTableModel.addRow();
            }
        });

        final JButton deleteButton = new JButton("Delete");
        leftBtnPanel.add(deleteButton);
        deleteButton.setMnemonic(KeyEvent.VK_D);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selection = table.getSelectedRows();
                for (int i = 0; i < selection.length; i++) {
                    selection[i] = table.convertRowIndexToModel(selection[i]);
                }

                collectionTableModel.deleteSelectedRows(selection);
            }
        });
        // When selection changes, enable or disable the Delete button
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                deleteButton.setEnabled(table.getSelectedRowCount() > 0);
            }
        });

        // buttonPanel.add(new JSeparator());

        // Save and Revert buttons should work together.
        saveButton = (JButton) rightBtnPanel.add(new JButton("Save"));
        saveButton.setMnemonic(KeyEvent.VK_S);
        saveButton.setEnabled(false);
        PresenterFirstRegistry.getInstance().registerMediaUpdateButton(saveButton);

        revertButton = (JButton) rightBtnPanel.add(new JButton("Revert"));
        revertButton.setMnemonic(KeyEvent.VK_R);
        revertButton.setEnabled(false);
        PresenterFirstRegistry.getInstance().registerMediaMaintButton(revertButton);

        // When data is modified, enable Save and Revert buttons
        collectionTableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                saveButton.setEnabled(true);
                revertButton.setEnabled(true);
            }
        });

        // When Save or Revert is executed, disable Save and Revert buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                revertButton.setEnabled(false);
            }
        });

        revertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.setEnabled(false);
                revertButton.setEnabled(false);
            }
        });

        container.validate();
        container.setVisible(true);
    }
}
