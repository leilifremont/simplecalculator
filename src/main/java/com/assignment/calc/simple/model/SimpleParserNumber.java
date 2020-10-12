/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

public class SimpleParserNumber extends SimpleParserExpression {

    public SimpleParserNumber(StringBuilder expressionBuilder) {
        super(expressionBuilder);
    }

    @Override
    protected void calculateResult() {
        String numberStr = this.expressionBuilder.toString();
        try {
            this.result = Integer.valueOf(numberStr);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException("Invalid number format for " + numberStr);
        }
    }
}
