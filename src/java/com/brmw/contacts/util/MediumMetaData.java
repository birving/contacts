package com.brmw.contacts.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Medium;

public class MediumMetaData extends AuditedBeanMetaData<Medium> {
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(MediumMetaData.class);

    private static final ColumnData<Medium> NAME_COLUMN = new AbstractColumnData<Medium>("Name") {
        @Override
        public Object getValue(Medium medium) {
            return medium.getName();
        }

        @Override
        public void setValue(Medium medium, Object value) {
            medium.setName((String) value);
        }
    };

    private static final ColumnData<Medium> TYPE_COLUMN = new AbstractColumnData<Medium>("Type") {
        @Override
        public Object getValue(Medium medium) {
            return medium.getType();
        }

        @Override
        public void setValue(Medium medium, Object value) {
            medium.setType((String) value);
        }
    };

    private static final ColumnData<Medium> NOTES_COLUMN = new AbstractColumnData<Medium>("Notes") {
        @Override
        public Object getValue(Medium medium) {
            return medium.getNotes();
        }

        @Override
        public void setValue(Medium medium, Object value) {
            medium.setNotes((String) value);
        }
    };

    @SuppressWarnings("unchecked")
    private List<ColumnData<Medium>> mediumColumnData = Arrays.asList(NAME_COLUMN, TYPE_COLUMN, NOTES_COLUMN);

    @Override
    public List<ColumnData<Medium>> getColumnData() {
        if (getIncludeDebugInfo()) {
            List<ColumnData<Medium>> colData = new ArrayList<ColumnData<Medium>>(mediumColumnData);
            colData.addAll(super.getColumnData());
            return colData;
        } else {
            return mediumColumnData;
        }
    }
}
