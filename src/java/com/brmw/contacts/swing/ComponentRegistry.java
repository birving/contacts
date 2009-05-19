package com.brmw.contacts.swing;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton to provide a handle to various Swing components for which access
 * may be needed throughout the ui code.
 */
public class ComponentRegistry {
    private static final ComponentRegistry registry = new ComponentRegistry();

    private Map<String, Component> components = new HashMap<String, Component>();

    public static ComponentRegistry getInstance() {
        return registry;
    }

    public void register(String name, Component component) {
        components.put(name, component);
    }

    public Component getComponent(String name) {
        return components.get(name);
    }
}
