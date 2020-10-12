/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

public enum SimpleParserState {
    START,
    NEGATIVE_SIGN,
    NUMBER,
    VARIABLE,
    OPERATOR,
    END,
    ERROR;
}
