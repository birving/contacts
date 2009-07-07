package com.brmw.contacts.swing;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

/**
 * Singleton to provide a handle to various Swing components for which access
 * may be needed throughout the ui code.
 */
public class ComponentRegistry {
    private static final ComponentRegistry registry = new ComponentRegistry();

    private Map<String, Object> objects = new HashMap<String, Object>();
    private Map<String, JComponent> components = new HashMap<String, JComponent>();
    // TODO: Consider using interface rather than concrete class for the
    // following ...
    private Map<String, CollectionTableDisplay<?>> collectionTables = new HashMap<String, CollectionTableDisplay<?>>();

    public static ComponentRegistry getInstance() {
        return registry;
    }

    public void register(String name, Object component) {
        objects.put(name, component);
    }

    public void register(String name, JComponent container) {
        components.put(name, container);
    }

    public void register(String name, CollectionTableDisplay<?> component) {
        collectionTables.put(name, component);
    }

    public Object get(String name) {
        return objects.get(name);
    }

    public JComponent getContainer(String name) {
        return components.get(name);
    }

    public CollectionTableDisplay<?> getCollectionTable(String name) {
        return collectionTables.get(name);
    }

    public void clear() {
        objects.clear();
        components.clear();
        collectionTables.clear();
    }
}
