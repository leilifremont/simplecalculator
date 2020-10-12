/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.logging;

public interface Logger {

    void debug(String s);

    void info(String s);

    void error(String s);

    void setLevel(String newLevel);
}
