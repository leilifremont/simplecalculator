/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleParserOperation extends SimpleParserExpression {

    protected String operator;
    protected int requiredOperands;
    protected List<SimpleParserExpression> operandsList=new ArrayList<>();

    public SimpleParserOperation(String operator, int requiredOperands) {
        super(new StringBuilder());
        this.operator = operator;
        this.requiredOperands = requiredOperands;
    }

    public boolean isReady() {
        return this.operandsList.size() == this.requiredOperands;
    }

    public void addOperand(SimpleParserExpression simpleParserExpression) {
        if (operandsList.size() == this.requiredOperands) {
            throw new RuntimeException("Exceed required operands");
        }
        operandsList.add(simpleParserExpression);
    }
}
