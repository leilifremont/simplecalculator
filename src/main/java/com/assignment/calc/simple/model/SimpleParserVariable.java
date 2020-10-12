/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

public class SimpleParserVariable extends SimpleParserExpression {

    public SimpleParserVariable(StringBuilder expressionBuilder) {
        super(expressionBuilder);
    }

    @Override
    protected void calculateResult() {
        String variableName = this.expressionBuilder.toString();
        if (!variables.containsKey(variableName)) {
            throw new RuntimeException("Not defined variable " + variableName);
        }
        this.result = variables.get(variableName).getResult();
    }
}
