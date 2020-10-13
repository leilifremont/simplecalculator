/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
import com.assignment.calc.simple.impl.SimpleParserFactory;
import com.assignment.calc.Parser;
import com.assignment.calc.ParserFactory;
import com.assignment.logging.Logger;
import com.assignment.logging.impl.SimpleLoggingFactory;
import com.assignment.logging.model.SimpleLoggingConstants;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private final static List<String> USAGE_LIST = new ArrayList<>();
    static {
        USAGE_LIST.add("A simple command line calculator");
        USAGE_LIST.add("Usage: java Calculator [-H] [-L <logLevel>] expression");
        USAGE_LIST.add("The following options are available:");
        USAGE_LIST.add("-L          to specify log level, support ERROR INFO DEBUG");
        USAGE_LIST.add("-H          to print this usage");
        USAGE_LIST.add("expression  support let/add/mult/div");
        USAGE_LIST.add("example     let(a, 5, add(a, a)) = 10");
    }

    public static void main(String[] args) {
        Logger logger=new SimpleLoggingFactory().getLogger();
        logger.setLevel(SimpleLoggingConstants.LOG_LEVEL_OFF);

        int length=args.length;
        if (length==0 || length==2 || length>3) {
            printHelp();
            return;
        } else if (length==1) {
            if ("-H".equalsIgnoreCase(args[0])) {
                printHelp();
                return;
            }
        } else if (length==3) {
            if (!("-L").equalsIgnoreCase(args[0])) {
                printHelp();
                return;
            }
            try {
                logger.setLevel(args[1]);
            } catch (Exception e) {
                printHelp();
                return;
            }
        }

        ParserFactory parserFactory = new SimpleParserFactory();
        Parser parser = parserFactory.makeNewInstance(logger);
        String input=args[length-1];
        int result=parser.parse(input);
        System.out.println(result);
    }

    private static void printHelp() {
        for (String line: USAGE_LIST) {
            System.out.println(line);
        }
    }
}
