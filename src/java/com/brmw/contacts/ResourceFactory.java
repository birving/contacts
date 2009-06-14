package com.brmw.contacts;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to lookup strings from a resource bundle
 */
public class ResourceFactory {
    private static final Logger logger = LoggerFactory.getLogger(ResourceFactory.class);
    private static final String DEFAULT_BASE_NAME = "contactsResources";
    private static ResourceFactory instance = new ResourceFactory();

    public static ResourceFactory getInstance() {
        return instance;
    }

    public static void setInstance(ResourceFactory instance) {
        ResourceFactory.instance = instance;
    }

    private ResourceBundle resources;
    private Set<String> keySet;

    public ResourceFactory() {
        this(DEFAULT_BASE_NAME);
    }

    public ResourceFactory(String baseName) {
        resources = ResourceBundle.getBundle(baseName);
        keySet = resources.keySet();
    }

    public Locale getLocaleInUse() {
        logger.debug("Default locale: {}; Effective locale: {}", Locale.getDefault(), resources.getLocale());
        return resources.getLocale();
    }

    public void setCurrentLocale() {
        resources = ResourceBundle.getBundle(DEFAULT_BASE_NAME);
        logger.debug("New default locale: {}; Effective locale: {}", Locale.getDefault(), resources.getLocale());
    }

    public String getString(String key) {
        return getString(key, null, true);
    }

    public String getString(String key, String defaultValue) {
        return getString(key, defaultValue, false);
    }

    private String getString(String key, String defaultValue, boolean required) {
        if (keySet.contains(key)) {
            return resources.getString(key);
        } else if (!required) {
            return defaultValue;
        } else {
            // TODO: Replace with custom Exception
            throw new RuntimeException("Missing resource: " + key);
        }
    }
}
