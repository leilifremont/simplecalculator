/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.error;

public class SimpleParserException extends RuntimeException {
    private SimpleParserErrorCode simpleParserErrorCode;
    private int index;

    public SimpleParserException(SimpleParserErrorCode simpleParserErrorCode, int index) {
        this.simpleParserErrorCode = simpleParserErrorCode;
        this.index = index;
    }

    public SimpleParserErrorCode getSimpleParserErrorCode() {
        return simpleParserErrorCode;
    }

    public void setSimpleParserErrorCode(SimpleParserErrorCode simpleParserErrorCode) {
        this.simpleParserErrorCode = simpleParserErrorCode;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ParserException{" +
                "errorCode=" + simpleParserErrorCode +
                ", index=" + index +
                '}';
    }
}
