package com.brmw.contacts.util;

import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameGeneratorTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(NameGeneratorTest.class);
    private NameGenerator nameGenerator;

    @Override
    protected void setUp() throws Exception {
        nameGenerator = new NameGenerator();
    }

    public void testOneName() {
        String displayName = "John";
        List<String> names = nameGenerator.generateVariants(displayName, null);
        assertEquals("Should be exactly one variant", 1, names.size());
    }

    public void testTwoNames() {
        String displayName = "John Smith";
        List<String> names = nameGenerator.generateVariants(displayName, null);
        assertEquals("Should be exactly two variants", 2, names.size());
        assertEquals("Smith, John", names.get(0));
        assertEquals("John Smith", names.get(1));
    }

    public void testTwoNamesExtraSpaces() {
        String displayName = "John  Smith  ";
        List<String> names = nameGenerator.generateVariants(displayName, null);
        assertEquals("Should be exactly two variants, spaces should be normalized", 2, names.size());
        assertEquals("Smith, John", names.get(0));
        assertEquals("John Smith", names.get(1));
    }

    public void testThreeNames() {
        String displayName = "John  Samuel  Smith  ";
        assertContains(displayName, "Smith, John", "John Samuel Smith", "Smith, John Samuel");
    }

    // Try some sample names to verify that the preferred option is present ...
    public void testHeleneDePurcell() {
        assertContains("Helene de Purcell", "de Purcell, Helene");
    }

    public void testJDDrew() {
        assertContains("J.D. Drew", "Drew, J.D.");
    }

    public void testDrJeanneAngel() {
        assertContains("Dr. Jeanne Angel", "Angel, Dr. Jeanne");
    }

    public void testJeanneIAngelPhd() {
        assertContains("Jeanne I. Angel, PHD", "Angel, Jeanne I. PHD");
    }

    public void testJohnKillhefferIII() {
        assertContains("John Killheffer III", "Killheffer, John III");
    }

    public void testJohnVKillhefferIII() {
        assertContains("John V. Killheffer III", "Killheffer, John V. III");
    }

    public void testJohnJGSmith() {
        assertContains("John Jacob Jingleheimer Schmidt", "Schmidt, John Jacob Jingleheimer");
    }

    private void assertContains(String displayName, String... shouldContain) {
        List<String> variants = nameGenerator.generateVariants(displayName, null);
        logger.debug("Options for: " + displayName + " (" + variants.size() + ")");
        for (String aName : variants) {
            logger.debug("    " + aName);
        }
        for (String name : shouldContain) {
            assertTrue("List should contain: " + name, variants.contains(name));
        }
        assertTrue("Too many choices: " + variants.size(), variants.size() <= 5);
    }
}
