/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleParserExpression {

    protected Integer result = null;
    protected Map<String, SimpleParserExpression> variables = new HashMap<>();
    protected StringBuilder expressionBuilder;

    public SimpleParserExpression(StringBuilder expressionBuilder) {
        this.expressionBuilder = expressionBuilder;
    }

    public int getResult() {
        if (this.result == null) {
            calculateResult();
        }
        return result;
    }

    public String getExpression() {
        return this.expressionBuilder.toString();
    }

    public void inheritVariables(SimpleParserExpression simpleParserExpression) {
        if (simpleParserExpression != null) {
            this.variables.putAll(simpleParserExpression.variables);
        }
    }

    abstract protected void calculateResult();
}
