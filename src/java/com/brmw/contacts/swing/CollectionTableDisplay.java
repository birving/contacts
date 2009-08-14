package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.brmw.contacts.domain.adaptor.TableMetaData;

/**
 * This class builds the Swing display for a JTable and associated buttons for
 * type T.
 */
public class CollectionTableDisplay<T extends AbstractBean> implements SelectableList<T> {
    private Container container;
    private CollectionTableModel<T> collectionTableModel;
    private TableMetaData<T> metaData;
    private JButton saveButton;
    private JButton revertButton;
    private SwingResourceFactory resourceFactory = SwingResourceFactory.getInstance();
    private JTable table;

    public CollectionTableDisplay(Collection<T> data, TableMetaData<T> metaData) {
        this.metaData = metaData;
        this.collectionTableModel = new CollectionTableModel<T>(data, metaData);
        this.table = new JTable(collectionTableModel);
        ComponentRegistry.getInstance().register(metaData.getTableName(), this);
        initialize();
    }

    /**
     * Override this method to add any extra initialization
     */
    protected void initialize() {
        // Do nothing
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    protected JTable getTable() {
        return table;
    }

    /**
     * Override this method to wire up Save button
     * 
     * @param button
     *            Save button
     */
    protected void registerSaveButton(AbstractButton button) {
        throw new UnsupportedOperationException("Save button has not been properly registered.");
    }

    /**
     * Override this method to wire up Revert button
     * 
     * @param button
     *            Revert button
     */
    protected void registerRevertButton(AbstractButton button) {
        throw new UnsupportedOperationException("Revert button has not been properly registered.");
    }

    /**
     * Update data in table
     * 
     * @param data
     *            the full collection of T objects
     */
    public void setTableData(Collection<T> data) {
        collectionTableModel.setTableData(data);
        setButtonEnabled(saveButton, false);
        setButtonEnabled(revertButton, false);
    }

    public Collection<T> getTableData() {
        return collectionTableModel.getTableData();
    }

    public void display() {
        if (container == null) {
            container = ComponentRegistry.getInstance().getContainer("CenterPanel");
        }
        container.removeAll();
        container.setLayout(new BorderLayout());

        createTableHeader(container);

        // final JTable table =
        createTable(container);

        createButtonPanel(container);

        container.validate();
        container.setVisible(true);
    }

    private void createTableHeader(Container container) {
        String headerText = resourceFactory.getString(metaData.getTableName() + ".tableHeader.text");
        String headerTooltip = resourceFactory.getString(metaData.getTableName() + ".tableHeader.tooltip", null);
        JLabel tableLabel = new JLabel(headerText, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText(headerTooltip);
        container.add(tableLabel, BorderLayout.NORTH);
    }

    private void createTable(Container container) {
        // final JTable table = new JTable(collectionTableModel);
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
        // return table;
    }

    private void createButtonPanel(Container container/* , final JTable table */) {
        JPanel buttonPanel = new JPanel(new BorderLayout());
        container.add(buttonPanel, BorderLayout.SOUTH);
        JPanel leftBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(leftBtnPanel, BorderLayout.WEST);
        JPanel rightBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(rightBtnPanel, BorderLayout.EAST);

        JButton addButton = resourceFactory.createButton(metaData.getTableName() + ".add");
        if (addButton != null) {
            leftBtnPanel.add(addButton);
            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    collectionTableModel.addRow();
                }
            });
        }

        final JButton deleteButton = resourceFactory.createButton(metaData.getTableName() + ".delete");
        if (deleteButton != null) {
            leftBtnPanel.add(deleteButton);
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
        }

        // Save and Revert buttons should work together.
        saveButton = resourceFactory.createButton(metaData.getTableName() + ".save");
        if (saveButton != null) {
            rightBtnPanel.add(saveButton);
            saveButton.setEnabled(false);
            registerSaveButton(saveButton);
        }

        revertButton = resourceFactory.createButton(metaData.getTableName() + ".revert");
        if (revertButton != null) {
            rightBtnPanel.add(revertButton);
            revertButton.setEnabled(false);
            registerRevertButton(revertButton);
        }

        if (saveButton != null || revertButton != null) {
            // When data is modified, enable Save and Revert buttons
            collectionTableModel.addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    setButtonEnabled(saveButton, true);
                    setButtonEnabled(revertButton, true);
                }
            });
        }

        // When Save or Revert is executed, disable Save and Revert buttons
        if (saveButton != null) {
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setButtonEnabled(saveButton, false);
                    setButtonEnabled(revertButton, false);
                }
            });
        }

        if (revertButton != null) {
            revertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setButtonEnabled(saveButton, false);
                    setButtonEnabled(revertButton, false);
                }
            });
        }
    }

    private void setButtonEnabled(AbstractButton button, boolean enabled) {
        if (button != null) {
            button.setEnabled(enabled);
        }
    }

    @Override
    public T getSelected(int index) {
        int modelIndex = table.convertRowIndexToModel(index);
        return collectionTableModel.getTableData().get(modelIndex);
    }
}
