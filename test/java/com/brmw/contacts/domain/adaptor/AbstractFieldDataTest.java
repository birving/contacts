package com.brmw.contacts.domain.adaptor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListResourceBundle;

import junit.framework.TestCase;

import com.brmw.contacts.ResourceFactory;
import com.brmw.contacts.domain.AbstractAuditedBean;
import com.brmw.contacts.domain.Audit;
import com.brmw.contacts.domain.Medium;
import com.brmw.contacts.domain.adaptor.BaseFieldData;

public class AbstractFieldDataTest extends TestCase {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void setUp() throws Exception {
        ResourceFactory.setInstance(new ResourceFactory(TestResourceBundle.class.getName()));
        assertEquals("resource.value", ResourceFactory.getInstance().getString("resource.key"));
    }

    public void testSimpleField() {
        BaseFieldData<Medium> fieldData = new BaseFieldData<Medium>(Medium.class, "test", "name");
        fieldData.setFieldEditable(true);

        assertNotNull(fieldData);
        assertEquals("Incorrect displayName:", "name", fieldData.getDisplayName());
        assertEquals("Incorrect fieldClass:", String.class, fieldData.getFieldClass());
        assertTrue("Expected fieldEditable to be true; was false", fieldData.isFieldEditable());

        Medium medium = new Medium();
        medium.setName("One test name");
        assertEquals("getValue() is return wrong value:", "One test name", fieldData.getValue(medium));

        fieldData.setValue(medium, "A different test name");
        assertEquals("setValue() is not setting value correctly:", "A different test name", medium.getName());
    }

    public void testBaseClassLongField() {
        BaseFieldData<Medium> fieldData = new BaseFieldData<Medium>(AbstractAuditedBean.class, "test", "id");
        fieldData.setFieldEditable(true);

        assertNotNull(fieldData);
        // Use resource defined below (in TestResource)
        assertEquals("Incorrect displayName:", "Identifier", fieldData.getDisplayName());
        assertEquals("Incorrect fieldClass:", Long.class, fieldData.getFieldClass());
        assertTrue("Expected fieldEditable to be true; was false", fieldData.isFieldEditable());

        Medium medium = new Medium();
        medium.setId(100L);

        assertEquals("getValue() is return wrong value:", 100L, fieldData.getValue(medium));

        fieldData.setValue(medium, 9876L);
        assertEquals("setValue() is not setting value correctly:", new Long(9876L), medium.getId());
    }

    public void testBaseClassNestedField() throws ParseException {
        BaseFieldData<Medium> fieldData = new BaseFieldData<Medium>(AbstractAuditedBean.class, "test", "created.transactionDate");
        fieldData.setFieldEditable(true);

        assertNotNull(fieldData);
        assertEquals("Incorrect displayName:", "Test Created Date", fieldData.getDisplayName());
        assertEquals("Incorrect fieldClass:", Date.class, fieldData.getFieldClass());
        assertTrue("Expected fieldEditable to be true; was false", fieldData.isFieldEditable());

        Audit created = new Audit();
        created.setId(200L);
        Date now = new Date(System.currentTimeMillis());
        created.setTransactionDate(now);
        Medium medium = new Medium();
        medium.setCreated(created);

        assertEquals("getValue() is return wrong value:", now, fieldData.getValue(medium));
        Date oTime = dateFormat.parse("2009-01-20 12:00:00");
        fieldData.setValue(medium, oTime);
        assertEquals("setValue() is not setting value correctly:", oTime, medium.getCreated().getTransactionDate());
    }

    public static class TestResourceBundle extends ListResourceBundle {

        @Override
        protected Object[][] getContents() {
            return new Object[][] { 
                    { "resource.key", "resource.value" },
                    { "test.col.name.text", "name" },
                    { "test.col.id.text", "Identifier" },
                    
                    { "test.col.created.transactionDate.text", "Test Created Date" },

            };
        }
    }
}
