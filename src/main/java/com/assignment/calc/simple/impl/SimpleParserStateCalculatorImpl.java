/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.impl;

import com.assignment.calc.simple.SimpleParserStateCalculator;
import com.assignment.calc.simple.model.SimpleParserState;

public class SimpleParserStateCalculatorImpl implements SimpleParserStateCalculator {

    /*
     * Calculate next parserState
     *
     * @param currentState
     * @param ch
     *
     * @return ParserState - next state
     *
     */
    @Override
    public SimpleParserState nextState(SimpleParserState currentState, char ch) {
        switch (currentState) {
            case START:
                return nextStateOfStart(ch);
            case NEGATIVE_SIGN:
                return nextStateOfNegativeSign(ch);
            case NUMBER:
                return nextStateOfNumber(ch);
            case OPERATOR:
                return nextStateOfOperator(ch);
            case END:
                return nextStateOfEnd(ch);
            default:
                return SimpleParserState.ERROR;
        }
    }

    private SimpleParserState nextStateOfEnd(char ch) {
        if (ch==',') {
            return SimpleParserState.START;
        } else if (ch==')') {
            return SimpleParserState.END;
        }

        return SimpleParserState.ERROR;
    }

    private SimpleParserState nextStateOfStart(char ch) {
        if (ch==' ') {
            return SimpleParserState.START;
        } else if (ch=='-') {
            return SimpleParserState.NEGATIVE_SIGN;
        } else if (Character.isDigit(ch)) {
            return SimpleParserState.NUMBER;
        } else if (Character.isLetter(ch)) {
            return SimpleParserState.OPERATOR;
        }

        return SimpleParserState.ERROR;
    }

    private SimpleParserState nextStateOfNegativeSign(char ch) {
        if (Character.isDigit(ch) && ch!='0') {
            return SimpleParserState.NUMBER;
        }

        return SimpleParserState.ERROR;
    }

    private SimpleParserState nextStateOfNumber(char ch) {
        if (Character.isDigit(ch)) {
            return SimpleParserState.NUMBER;
        } else if (ch==',' || ch==')') {
            return SimpleParserState.END;
        }

        return SimpleParserState.ERROR;
    }

    private SimpleParserState nextStateOfOperator(char ch) {
        if (ch=='(') {
            return SimpleParserState.START;
        } else if (Character.isLetter(ch)) {
            return SimpleParserState.OPERATOR;
        } else if (ch==',' || ch==')') {
            return SimpleParserState.VARIABLE;
        }

        return SimpleParserState.ERROR;
    }
}
