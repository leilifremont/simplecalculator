/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc;

import com.assignment.logging.Logger;

public interface ParserFactory {

	  Parser makeNewInstance(Logger logger);
}
