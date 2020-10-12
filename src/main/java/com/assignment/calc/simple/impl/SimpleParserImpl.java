/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.impl;

import com.assignment.calc.Parser;
import com.assignment.calc.simple.SimpleParserStateCalculator;
import com.assignment.calc.simple.error.SimpleParserErrorCode;
import com.assignment.calc.simple.error.SimpleParserException;
import com.assignment.calc.simple.model.*;
import com.assignment.logging.Logger;
import com.assignment.logging.impl.SimpleLoggingFactory;

import java.util.*;

public class SimpleParserImpl implements Parser {

    private SimpleParserStateCalculator simpleParserStateCalculator = new SimpleParserStateCalculatorImpl();
    private Logger logger = new SimpleLoggingFactory().getLogger();

    public SimpleParserImpl(Logger logger) {
        if (logger != null) {
            this.logger = logger;
        }
    }

    @Override
    public int parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            logger.info("return 0 for empty input");
            return 0;
        }

        logger.info("Start parsing for " + expression);
        Deque<SimpleParserExpression> stack = new ArrayDeque<>();
        SimpleParserExpression currentParserExpression = null;
        StringBuilder expressionBuilder = new StringBuilder();
        SimpleParserState currentParserState = SimpleParserState.START;

        for (int i=0; i<=expression.length(); i++) {
            logger.debug("At position " + i + ", currentParserState is " + currentParserState);
            logger.debug("At position " + i + ", stack is " + stack);
            logger.debug("At position " + i + ", expressionBuilder is " + expressionBuilder);
            logger.debug("At position " + i + ", currentParserExpression is " + currentParserExpression);
            SimpleParserState nextParserState = i == expression.length() ? SimpleParserState.END :
                    simpleParserStateCalculator.nextState(currentParserState, expression.charAt(i));
            logger.debug("At position " + i + ", nextParserState is " + nextParserState);
            switch (currentParserState) {
                case START:
                    switch (nextParserState) {
                        case NEGATIVE_SIGN:
                        case NUMBER:
                        case OPERATOR:
                            if (currentParserExpression != null) {
                                stack.push(currentParserExpression);
                                currentParserExpression = null;
                                expressionBuilder = new StringBuilder();
                            }
                            expressionBuilder.append(expression.charAt(i));
                            break;
                    }
                    break;
                case NEGATIVE_SIGN:
                    switch (nextParserState) {
                        case NUMBER:
                            expressionBuilder.append(expression.charAt(i));
                            break;
                    }
                    break;
                case NUMBER:
                    switch (nextParserState) {
                        case NUMBER:
                            expressionBuilder.append(expression.charAt(i));
                            break;
                        case END:
                            currentParserExpression = new SimpleParserNumber(expressionBuilder);
                            if (!stack.isEmpty()) {
                                SimpleParserOperation simpleParserOperation = (SimpleParserOperation)stack.peek();
                                simpleParserOperation.addOperand(currentParserExpression);
                                currentParserExpression = null;
                                if (simpleParserOperation.isReady()) {
                                    currentParserExpression = stack.pop();
                                } else {
                                    // restart to read next operand in this case
                                    nextParserState = SimpleParserState.START;
                                }
                                expressionBuilder = new StringBuilder();
                            }
                            break;
                    }
                    break;
                case OPERATOR:
                    switch (nextParserState) {
                        case OPERATOR:
                            expressionBuilder.append(expression.charAt(i));
                            break;
                        case VARIABLE:
                            if (!stack.isEmpty()) {
                                SimpleParserOperation simpleParserOperation = (SimpleParserOperation)stack.peek();
                                SimpleParserVariable simpleParserVariable = new SimpleParserVariable(expressionBuilder);
                                simpleParserVariable.inheritVariables(simpleParserOperation);
                                simpleParserOperation.addOperand(simpleParserVariable);
                                expressionBuilder = new StringBuilder();

                                if (simpleParserOperation.isReady()) {
                                    currentParserExpression = stack.pop();
                                    // need not read any more operand in this case
                                    nextParserState = SimpleParserState.END;
                                } else {
                                    // restart to read next operand in this case
                                    nextParserState = SimpleParserState.START;
                                }
                            }
                            break;
                        case START:
                            try {
                                String operation = expressionBuilder.toString();
                                currentParserExpression =
                                        SimpleParserOperationFactory.createSimpleParserOperation(operation);
                                if (!stack.isEmpty()) {
                                    currentParserExpression.inheritVariables(stack.peek());
                                }
                                stack.push(currentParserExpression);
                                currentParserExpression = null;
                                expressionBuilder = new StringBuilder();
                            } catch (UnsupportedOperationException e) {
                                // invalid operator, should be ADD / MULT / DIV / LET
                                nextParserState = SimpleParserState.ERROR;
                            }
                            break;
                    }
                    break;
                case END:
                    switch (nextParserState) {
                        case START:
                        case END:
                            if (!stack.isEmpty() && currentParserExpression!=null) {
                                SimpleParserOperation simpleParserOperation = (SimpleParserOperation)stack.peek();
                                simpleParserOperation.addOperand(currentParserExpression);
                                currentParserExpression = null;
                                if (simpleParserOperation.isReady()) {
                                    currentParserExpression = stack.pop();
                                } else {
                                    // restart to read next operand in this case
                                    nextParserState = SimpleParserState.START;
                                }
                                expressionBuilder = new StringBuilder();
                            }
                            break;
                    }
                    break;
                default:
                    // Do nothing by default
            }

            logger.debug("At position " + i + ", after processing, nextParserState is " + nextParserState);
            currentParserState = nextParserState;
            if (currentParserState == SimpleParserState.ERROR) {
                logger.error("Error found at position [" + i + "] of " + expression);
                throw new SimpleParserException(SimpleParserErrorCode.INVALID_EXPRESSION, i);
            }
        }

        int result = currentParserExpression == null ? 0 : currentParserExpression.getResult();
        logger.info("Result is " + result + " for " + expression);
        return result;
    }
}