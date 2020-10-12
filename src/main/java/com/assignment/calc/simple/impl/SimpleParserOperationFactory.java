/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.impl;

import com.assignment.calc.simple.model.*;

public class SimpleParserOperationFactory {

    public static SimpleParserOperation createSimpleParserOperation(String operation) {
        switch (operation) {
            case SimpleParserConstants.ADD:
                return new SimpleParserAddOperation();
            case SimpleParserConstants.DIV:
                return new SimpleParserDivOperation();
            case SimpleParserConstants.MULT:
                return new SimpleParserMultOperation();
            case SimpleParserConstants.LET:
                return new SimpleParserLetOperation();
            default:
                throw new UnsupportedOperationException("Not supported operation " + operation);
        }
    }
}
