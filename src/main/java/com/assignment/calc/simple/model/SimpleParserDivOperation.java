/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.model;

public class SimpleParserDivOperation extends SimpleParserOperation {
    public SimpleParserDivOperation() {
        super(SimpleParserConstants.DIV, 2);
    }

    public void calculateResult() {
        if (this.operandsList.size() != this.requiredOperands) {
            throw new RuntimeException("invalid operands size " + this.operandsList.size());
        }
        this.result = this.operandsList.get(0).getResult() / this.operandsList.get(1).getResult();
    }
}
