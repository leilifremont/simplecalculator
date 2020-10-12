/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.logging.impl;

import com.assignment.logging.Logger;
import com.assignment.logging.LoggingFactory;

public class SimpleLoggingFactory implements LoggingFactory {

    private static final Logger LOGGER = new SimpleLoggingLogger();

    @Override
    public Logger getLogger() {
        return LOGGER;
    }
}
