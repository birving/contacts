package com.brmw.contacts.swing;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton to provide a handle to various Swing components for which access
 * may be needed throughout the ui code.
 */
public class ComponentRegistry {
    private static final ComponentRegistry registry = new ComponentRegistry();

    private Map<String, Object> components = new HashMap<String, Object>();

    public static ComponentRegistry getInstance() {
        return registry;
    }

    public void register(String name, Object component) {
        components.put(name, component);
    }

    public Object getComponent(String name) {
        return components.get(name);
    }

    public void clear() {
        components.clear();
    }
}
