/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

public class SimpleParserLetOperation extends SimpleParserOperation {
    public SimpleParserLetOperation() {
        super(SimpleParserConstants.LET, 3);
    }

    @Override
    public void calculateResult() {
        if (this.operandsList.size() != this.requiredOperands) {
            throw new RuntimeException("invalid operands size " + this.operandsList.size());
        }

        this.result = this.operandsList.get(2).getResult();
    }

    @Override
    public void addOperand(SimpleParserExpression simpleParserExpression) {
        super.addOperand(simpleParserExpression);
        if (this.operandsList.size() == 2) {
            this.variables.put(this.operandsList.get(0).getExpression(), this.operandsList.get(1));
        } else if (this.operandsList.size() == 3) {
            this.operandsList.get(2).inheritVariables(this);
        }
    }
}
