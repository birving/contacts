package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Collection;

import javax.swing.AbstractButton;
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

import com.brmw.contacts.domain.AbstractBean;
import com.brmw.contacts.util.TableMetaData;

/**
 * This class builds the Swing display for a JTable and associated buttons for
 * type T.
 */
public abstract class CollectionTableDisplay<T extends AbstractBean> {
    private CollectionTableModel<T> collectionTableModel;
    private TableMetaData<T> metaData;
    private JButton saveButton;
    private JButton revertButton;
    private ResourceFactory resourceFactory = ResourceFactory.getInstance();

    public CollectionTableDisplay(Collection<T> data, TableMetaData<T> metaData) {
        this.metaData = metaData;
        this.collectionTableModel = new CollectionTableModel<T>(data, metaData);
        ComponentRegistry.getInstance().register(metaData.getRegistryKey(), this);
    }

    /**
     * Implement this method to wire up Save button
     * 
     * @param button
     *            Save button
     */
    abstract protected void registerSaveButton(AbstractButton button);

    /**
     * Implement this method to wire up Revert button
     * 
     * @param button
     *            Revert button
     */
    abstract protected void registerRevertButton(AbstractButton button);

    /**
     * Update data in table
     * 
     * @param data
     *            the full collection of T objects
     */
    public void setTableData(Collection<T> data) {
        collectionTableModel.setTableData(data);
        saveButton.setEnabled(false);
        revertButton.setEnabled(false);
    }

    public Collection<T> getTableData() {
        return collectionTableModel.getTableData();
    }

    public void display() {
        Container container = ComponentRegistry.getInstance().getContainer("CenterPanel");
        container.removeAll();
        container.setLayout(new BorderLayout());

        createTableHeader(container);

        final JTable table = createTable(container);

        createButtonPanel(container, table);

        container.validate();
        container.setVisible(true);
    }

    private void createTableHeader(Container container) {
        String headerText = resourceFactory.getString(metaData.getRegistryKey() + ".tableHeader.text");
        String headerTooltip = resourceFactory.getString(metaData.getRegistryKey() + ".tableHeader.tooltip", null);
        JLabel tableLabel = new JLabel(headerText, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText(headerTooltip);
        container.add(tableLabel, BorderLayout.NORTH);
    }

    private JTable createTable(Container container) {
        final JTable table = new JTable(collectionTableModel);
        table.setFillsViewportHeight(true);

        if (metaData.isDeleteable()) {
            TableRowSorter<CollectionTableModel<T>> rowSorter =
                    new TableRowSorter<CollectionTableModel<T>>(collectionTableModel);
            rowSorter.setRowFilter(new RowFilter<CollectionTableModel<T>, Integer>() {
                @Override
                public boolean include(
                        javax.swing.RowFilter.Entry<? extends CollectionTableModel<T>, ? extends Integer> entry) {
                    CollectionTableModel<T> tableModel = entry.getModel();
                    T data = tableModel.getTableData().get(entry.getIdentifier());
                    return !data.isDeleted();
                }
            });
            table.setRowSorter(rowSorter);
        } else {
            table.setAutoCreateRowSorter(true);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane, BorderLayout.CENTER);
        return table;
    }

    private void createButtonPanel(Container container, final JTable table) {
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

        // Save and Revert buttons should work together.
        saveButton = (JButton) rightBtnPanel.add(new JButton("Save"));
        saveButton.setMnemonic(KeyEvent.VK_S);
        saveButton.setEnabled(false);
        registerSaveButton(saveButton);

        revertButton = (JButton) rightBtnPanel.add(new JButton("Revert"));
        revertButton.setMnemonic(KeyEvent.VK_R);
        revertButton.setEnabled(false);
        registerRevertButton(revertButton);

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
    }
}
