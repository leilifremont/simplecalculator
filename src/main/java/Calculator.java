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
        List<String> list=new ArrayList<>();
        list.add("Simple command line calculator");
        list.add("Usage: java Calculator [-H] [-L <logLevel>] expression");
        list.add("The following options are available:");
        list.add("-L      to specify log level, support ERROR INFO DEBUG");
        list.add("-H      to print this usage");
        for (String line:list) {
            System.out.println(line);
        }
    }
}
