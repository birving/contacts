package com.brmw.contacts.swing;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.util.MediumMetaData;
import com.brmw.contacts.view.MediaMaintView;

/**
 * Media Maintenance GUI elements.
 * 
 * @author bruce
 * 
 */
public class MediaMaintViewImpl implements MediaMaintView {
    private static final Logger logger = LoggerFactory.getLogger(MediaMaintViewImpl.class);
    private AbstractButton allMediaButton;

    protected MediaMaintViewImpl(AbstractButton allMediaButton) {
        this.allMediaButton = allMediaButton;
    }

    public JComponent getComponent() {
        return allMediaButton;
    }

    @Override
    public void addAllMediaRequestListener(ActionListener actionListener) {
        allMediaButton.addActionListener(actionListener);
    }

    @Override
    public void displayMedia(Collection<Medium> media) {
        logger.debug("Running MediaMaintViewImpl.displayMedia();" );
        TableModel mediaTableModel = new CollectionTableModel<Medium>(media, new MediumMetaData());
        JTable table = new JTable(mediaTableModel);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);

        Container container = (Container) ComponentRegistry.getInstance().getComponent("CenterPanel");
        container.add(scrollPane);
        container.validate();
        container.setVisible(true);
    }

}
