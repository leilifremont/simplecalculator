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

    @Override
    public void calculateResult() {
        if (this.operandsList.size() != this.requiredOperands) {
            throw new RuntimeException("Invalid operands size, expected " + this.requiredOperands
                    + ", actual " + this.operandsList.size());
        }
        this.result = this.operandsList.get(0).getResult() / this.operandsList.get(1).getResult();
    }
}
