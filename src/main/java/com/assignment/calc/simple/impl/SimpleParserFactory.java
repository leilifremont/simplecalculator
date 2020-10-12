/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc.simple.impl;

import com.assignment.calc.Parser;
import com.assignment.calc.ParserFactory;
import com.assignment.logging.Logger;

public class SimpleParserFactory implements ParserFactory {

    @Override
    public Parser makeNewInstance(Logger logger) {
        Parser parser = new SimpleParserImpl(logger);
        logger.debug("will return a new parser " + parser);
        return parser;
    }
}
