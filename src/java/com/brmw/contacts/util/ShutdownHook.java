package com.brmw.contacts.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class can be used to run any required cleanup code before the
 * application exits.
 * 
 */
public class ShutdownHook implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    @Override
    public void run() {
        logger.debug("Running Contacts application shutdown hook.");
    }
}
