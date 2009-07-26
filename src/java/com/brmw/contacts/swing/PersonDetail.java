package com.brmw.contacts.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.brmw.contacts.domain.Locator;
import com.brmw.contacts.domain.Person;
import com.brmw.contacts.domain.adaptor.LocatorMetaData;
import com.brmw.contacts.mvp.PresenterFirstRegistry;

/**
 * This class builds the Swing display for details of a Person.
 */
public class PersonDetail {
    private JComponent personContainer;
    private String pageName = "personDetail";
    private Person person;
    private SwingResourceFactory resourceFactory = SwingResourceFactory.getInstance();

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

    private JTable createTable(JComponent container2) {
        // TODO Auto-generated method stub
        return null;
    }

    private void createHeader(JComponent container, Object constraint) {
        String headerText = resourceFactory.getString(pageName + ".header.text");
        String headerTooltip = resourceFactory.getString(pageName + ".tableHeader.tooltip", null);
        JLabel tableLabel = new JLabel(headerText, SwingConstants.CENTER);
        tableLabel.setFont(ContactsConstants.HEADER_FONT);
        tableLabel.setToolTipText(headerTooltip);
        container.add(tableLabel, constraint);
    }

    private void createNameSection(JComponent container, Object constraint) {

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        container.add(panel, constraint);

        // Use automated padding ...
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        String fullNameText = resourceFactory.getString(pageName + ".field.fullName.text") + ":";
        JLabel fullNameLabel = new JLabel(fullNameText, SwingConstants.LEADING);
        JTextField fullNameField = new JTextField(person.getFirstName() + " " + person.getLastName(), 15);

        String roleText = resourceFactory.getString(pageName + ".field.role.text") + ":";
        JLabel roleLabel = new JLabel(roleText);
        JTextField roleField = new JTextField(person.getRole(), 15);

        String companyText = resourceFactory.getString(pageName + ".field.company.text") + ":";
        JLabel companyLabel = new JLabel(companyText);
        JTextField companyField = new JTextField(person.getCompany().toString(), 15);

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
                                  .addComponent(fullNameLabel)
                                  .addComponent(roleLabel)
                                  .addComponent(companyLabel))
                .addGroup(
                          layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                  .addComponent(
                                                fullNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
                                                    .addComponent(fullNameLabel)
                                                    .addComponent(fullNameField))
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

    private void createButtonPanel(JComponent container2, JTable table) {
        // TODO Auto-generated method stub
    }
}
