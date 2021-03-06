package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.brmw.contacts.domain.Locator;
import com.brmw.contacts.domain.Person;
import com.brmw.contacts.domain.adaptor.LocatorMetaData;
import com.brmw.contacts.mvp.PresenterFirstRegistry;
import com.brmw.contacts.util.NameGenerator;

/**
 * This class builds the Swing display for details of a Person.
 */
public class PersonDetail {
    private JComponent personContainer;
    private String pageName = "personDetail";
    private Person person;
    private SwingResourceFactory resourceFactory = SwingResourceFactory.getInstance();
    // private JTextField uniqueNameField;
    private JComboBox uniqueNameField;
    private NameGenerator nameGenerator = new NameGenerator();

    public Container getPersonContainer() {
        return personContainer;
    }

    public void setPersonContainer(JComponent container) {
        this.personContainer = container;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void display() {
        if (personContainer == null) {
            personContainer = ComponentRegistry.getInstance().getContainer("CenterPanel");
        }
        personContainer.removeAll();
        personContainer.setLayout(new BorderLayout());

        createHeader(personContainer, BorderLayout.PAGE_START);
        // final JTable table = createTable(personContainer);

        JPanel innerContainer = new JPanel(new BorderLayout());
        personContainer.add(innerContainer, BorderLayout.CENTER);

        createNameSection(innerContainer, BorderLayout.PAGE_START);

        JSplitPane splitPane =
                new JSplitPane(JSplitPane.VERTICAL_SPLIT, createLocatorPanel(), createCommunicationPanel());
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
        // splitPane.setContinuousLayout(true);
        splitPane.setOneTouchExpandable(true);

        innerContainer.add(splitPane, BorderLayout.CENTER);
        // createButtonPanel(personContainer, table);

        personContainer.validate();
        personContainer.setVisible(true);
    }

    // private JTable createTable(JComponent container2) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    private void createHeader(JComponent container, Object constraint) {
        String headerText = resourceFactory.getString(pageName + ".header.text");
        String headerTooltip = resourceFactory.getString(pageName + ".tableHeader.tooltip", null);
        JLabel tableLabel = new JLabel(headerText, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText(headerTooltip);
        container.add(tableLabel, constraint);
    }

    private void updateUniqueFromDisplay(DocumentEvent event) {
        // if (person.getUniqueName() == null ||
        // person.getUniqueName().isEmpty()) {
        Document doc = event.getDocument();
        String text = null;
        try {
            text = (doc.getLength() == 0) ? "" : doc.getText(0, doc.getLength());
        } catch (BadLocationException e1) {
            // This should never happen
            throw new IllegalStateException("Unable to get text from text field", e1);
        }
        List<String> names = nameGenerator.generateVariants(text, null);
        uniqueNameField.setSelectedItem(names.get(0));
        // }
    }

    private void createNameSection(JComponent container, Object constraint) {

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        container.add(panel, constraint);

        // Use automated padding ...
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String displayNameText = resourceFactory.getString(pageName + ".field.displayName.text") + ":";
        JLabel displayNameLabel = new JLabel(displayNameText, SwingConstants.LEADING);
        JTextField displayNameField = new JTextField(person.getDisplayName(), 15);

        String uniqueNameText = resourceFactory.getString(pageName + ".field.uniqueName.text") + ":";
        JLabel uniqueNameLabel = new JLabel(uniqueNameText, SwingConstants.LEADING);
        // uniqueNameField = new JTextField(person.getUniqueName(), 15);

        List<String> names = nameGenerator.generateVariants(person.getDisplayName(), person.getUniqueName());
        uniqueNameField = new JComboBox(names.toArray());
        uniqueNameField.setEditable(true);
        uniqueNameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uniqueName = uniqueNameField.getSelectedItem().toString();
                person.setUniqueName(uniqueName);
                // int index = uniqueNameField.getSelectedIndex();
                // if (index < 0) {
                // uniqueNameField.insertItemAt(uniqueName, 0);
                // }

                // List<String> names =
                // nameGenerator.generateVariants(person.getDisplayName(),
                // person.getUniqueName());
                // uniqueNameField.removeAllItems();
                // uniqueNameField.addItem(anObject);
            }
        });

        displayNameField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { // ignore
            }

            public void insertUpdate(DocumentEvent e) {
                updateUniqueFromDisplay(e);
            }

            public void removeUpdate(DocumentEvent e) {
                updateUniqueFromDisplay(e);
            }
        });

        String roleText = resourceFactory.getString(pageName + ".field.role.text") + ":";
        JLabel roleLabel = new JLabel(roleText);
        JTextField roleField = new JTextField(person.getRole(), 15);

        String companyText = resourceFactory.getString(pageName + ".field.company.text") + ":";
        JLabel companyLabel = new JLabel(companyText);
        JTextField companyField =
                new JTextField((person.getCompany() == null) ? "" : person.getCompany().toString(), 15);

        String notesText = resourceFactory.getString(pageName + ".field.notes.text") + ":";
        JLabel notesLabel = new JLabel(notesText);

        JTextArea notesField = new JTextArea(person.getNotes(), 3, 15);
        notesField.setLineWrap(true);
        notesField.setWrapStyleWord(true);

        JScrollPane notesPane = new JScrollPane(notesField);
        // notesPane.setBorder(BorderFactory.createTitledBorder(notesText));
        notesPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(
                          layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                  .addComponent(displayNameLabel)
                                  .addComponent(uniqueNameLabel)
                                  .addComponent(roleLabel)
                                  .addComponent(companyLabel))
                .addGroup(
                          layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addComponent(
                                                displayNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                  .addComponent(
                                                uniqueNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                Short.MAX_VALUE)
                                  .addComponent(roleField)
                                  .addComponent(companyField))
                .addComponent(notesLabel)
                .addComponent(notesPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false)
                .addGroup(
                          layout.createSequentialGroup()
                                  .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(displayNameLabel)
                                                    .addComponent(displayNameField))
                                  .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(uniqueNameLabel)
                                                    .addComponent(uniqueNameField))
                                  .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(roleLabel)
                                                    .addComponent(roleField))
                                  .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                    .addComponent(companyLabel)
                                                    .addComponent(companyField)))
                // .addComponent(notesPane));
                .addGroup(layout.createParallelGroup().addComponent(notesLabel).addComponent(notesPane)));
    }

    private JComponent createLocatorPanel() {
        Collection<Locator> locators = person.getLocators();
        JPanel locatorPanel = new JPanel();
        String borderTitle = resourceFactory.getString(pageName + ".locators.header.text");
        TitledBorder border = BorderFactory.createTitledBorder(borderTitle);
        locatorPanel.setBorder(border);

        CollectionTableDisplay<Locator> locatorDisplay =
                new CollectionTableDisplay<Locator>(locators, new LocatorMetaData(pageName + ".")) {
                    @Override
                    protected void registerSaveButton(AbstractButton button) {
                        PresenterFirstRegistry.getInstance().registerMediaUpdateButton(button);
                    }
                };
        locatorDisplay.setContainer(locatorPanel);
        locatorDisplay.display();

        return locatorPanel;
    }

    private JComponent createCommunicationPanel() {
        JPanel communicationsPanel = new JPanel();
        String borderTitle = resourceFactory.getString(pageName + ".communications.header.text");
        TitledBorder border = BorderFactory.createTitledBorder(borderTitle);
        communicationsPanel.setBorder(border);
        return communicationsPanel;
    }

    // private void createButtonPanel(JComponent container2, JTable table) {
    // // TODO Auto-generated method stub
    // }
}
