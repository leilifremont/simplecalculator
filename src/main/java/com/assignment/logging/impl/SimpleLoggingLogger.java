/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.logging.impl;

import ch.qos.logback.classic.Level;
import com.assignment.logging.Logger;
import com.assignment.logging.model.SimpleLoggingConstants;
import org.slf4j.LoggerFactory;

public class SimpleLoggingLogger implements Logger {
    private static final ch.qos.logback.classic.Logger logger=(ch.qos.logback.classic.Logger)
            LoggerFactory.getLogger(SimpleLoggingLogger.class);

    @Override
    public void debug(String s) {
        logger.debug(s);
    }

    @Override
    public void info(String s) {
        logger.info(s);
    }

    @Override
    public void error(String s) {
        logger.error(s);
    }

    @Override
    public void setLevel(String newLevel) {
        if (SimpleLoggingConstants.LOG_LEVEL_OFF.equalsIgnoreCase(newLevel)) {
            logger.setLevel(Level.OFF);
        } else if (SimpleLoggingConstants.LOG_LEVEL_INFO.equalsIgnoreCase(newLevel)){
            logger.setLevel(Level.INFO);
        } else if (SimpleLoggingConstants.LOG_LEVEL_DEBUG.equalsIgnoreCase(newLevel)) {
            logger.setLevel(Level.DEBUG);
        } else if (SimpleLoggingConstants.LOG_LEVEL_ERROR.equalsIgnoreCase(newLevel)) {
            logger.setLevel(Level.ERROR);
        } else {
            throw new UnsupportedOperationException("Unsupported log level " + newLevel);
        }
    }
}
