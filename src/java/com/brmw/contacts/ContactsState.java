package com.brmw.contacts;

/**
 * Convenience class to hold static application state information.
 */
public class ContactsState {
    private static Boolean debugMode = Boolean.FALSE;

    public static Boolean isDebugMode() {
        return debugMode;
    }

    public static void setDebugMode(Boolean debugMode) {
        ContactsState.debugMode = debugMode;
    }
}
