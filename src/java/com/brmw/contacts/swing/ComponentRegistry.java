package com.brmw.contacts.swing;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton to provide a handle to various Swing components for which access
 * may be needed throughout the ui code.
 */
public class ComponentRegistry {
    private static final ComponentRegistry registry = new ComponentRegistry();

    private Map<String, Object> objects = new HashMap<String, Object>();
    private Map<String, Container> containers = new HashMap<String, Container>();
    // TODO: Consider using interface rather than concrete class for the
    // following ...
    private Map<String, CollectionTableDisplay<?>> collectionTables = new HashMap<String, CollectionTableDisplay<?>>();

    public static ComponentRegistry getInstance() {
        return registry;
    }

    public void register(String name, Object component) {
        objects.put(name, component);
    }

    public void register(String name, Container container) {
        containers.put(name, container);
    }

    public void register(String name, CollectionTableDisplay<?> component) {
        collectionTables.put(name, component);
    }

    public Object get(String name) {
        return objects.get(name);
    }

    public Container getContainer(String name) {
        return containers.get(name);
    }

    public CollectionTableDisplay<?> getCollectionTable(String name) {
        return collectionTables.get(name);
    }

    public void clear() {
        objects.clear();
        containers.clear();
        collectionTables.clear();
    }
}
