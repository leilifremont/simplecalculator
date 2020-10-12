/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.error;

public enum SimpleParserErrorCode {

    INVALID_EXPRESSION(100, "Invalid expression detected");

    private int id;
    private String message;

    SimpleParserErrorCode(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}