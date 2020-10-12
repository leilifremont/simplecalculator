/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple;

import com.assignment.calc.simple.model.SimpleParserState;

public interface SimpleParserStateCalculator {

    /*
     * Calculate next parserState
     *
     * @param currentState
     * @param ch
     *
     * @return ParserState - next state
     *
     */
    SimpleParserState nextState(SimpleParserState currentState, char ch);
}
