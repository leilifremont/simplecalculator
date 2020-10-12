/* *************************************************
 * Copyright 2020
 *
 *
 * *************************************************/
package com.assignment.calc;

import com.assignment.calc.simple.error.SimpleParserErrorCode;
import com.assignment.calc.simple.error.SimpleParserException;
import com.assignment.calc.simple.impl.SimpleParserFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.assignment.logging.impl.SimpleLoggingFactory;

import java.util.Random;

public class SimpleParserUnitTest {

    private Parser parser;
    private static final int RANDOM_TEST_ROUNDS = 10;

    @Before
    public void setup() {
        this.parser = new SimpleParserFactory().makeNewInstance(new SimpleLoggingFactory().getLogger());
    }

    @Test
    public void testParseEmptyInput() {
        String input="";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 0);
    }

    @Test
    public void testParseNullInput() {
        String input=null;
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 0);
    }

    @Test
    public void testParseZero() {
        String input="0";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 0);
    }

    @Test
    public void testParseNegativeZero() {
        String input="-0";
        try {
            int result=this.parser.parse(input);
            Assert.fail("Exception expected");
        } catch (SimpleParserException pe) {
            Assert.assertTrue(pe.getSimpleParserErrorCode() == SimpleParserErrorCode.INVALID_EXPRESSION);
        }
    }

    @Test
    public void testParseMultipleNegativeZeroes() {
        String input="-00001";
        try {
            int result=this.parser.parse(input);
            Assert.fail("Exception expected");
        } catch (SimpleParserException pe) {
            Assert.assertTrue(pe.getSimpleParserErrorCode() == SimpleParserErrorCode.INVALID_EXPRESSION);
        }
    }

    @Test
    public void testParseNegativeZeroes() {
        String input="-00";
        try {
            int result=this.parser.parse(input);
            Assert.fail("Exception expected");
        } catch (SimpleParserException pe) {
            Assert.assertTrue(pe.getSimpleParserErrorCode() == SimpleParserErrorCode.INVALID_EXPRESSION);
        }
    }

    @Test
    public void testParsePostiveNumber() {
        String input="12345";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 12345);
    }

    @Test
    public void testParsePostiveNumberRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            String input=String.valueOf(Math.abs(a));
            int result=this.parser.parse(input);
            Assert.assertTrue(result == Math.abs(a));
        }
    }

    @Test
    public void testParseNegativeNumber() {
        String input="-54321";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == -54321);
    }

    @Test
    public void testParseNegativeNumberRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            String input=String.valueOf(-Math.abs(a));
            int result=this.parser.parse(input);
            Assert.assertTrue(result == -Math.abs(a));
        }
    }

    @Test
    public void testParseAdd() {
        int a=21;
        int b=678;
        String input = "add("+a+","+b+")";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == a+b);
    }

    @Test
    public void testParseAddRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "add("+a+","+b+")";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a+b);
        }
    }

    @Test
    public void testParseMultRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "mult("+a+","+b+")";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a*b);
        }
    }

    @Test
    public void testParseDivRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "div("+a+","+b+")";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a/b);
        }
    }

    @Test
    public void testParseAddDivMultRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            int c=random.nextInt();
            int d=random.nextInt();
            String input = "add("+a+",div("+b+",mult("+c+","+d+")))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a+b/(c*d));
        }
    }

    @Test
    public void testParseMultAddDivRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            int c=random.nextInt();
            int d=random.nextInt();
            int e=random.nextInt();
            int f=random.nextInt();
            String input = "mult(div("+b+",add("+c+","+d+")),"+"add("+d+",mult("+e+","+f+")))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == b/(c+d)*(d+e*f));
        }
    }

    @Test
    public void testParseDivMultAddRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            int c=random.nextInt();
            int d=random.nextInt();
            String input = "div("+a+",mult(add("+b+","+c+"),"+d+"))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a/((b+c)*d));
        }
    }

    @Test
    public void testParseMultDiv() {
        String input = "mult(13,div(6,3))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 13*(6/3));
    }

    @Test
    public void testParseAddDiv() {
        String input = "add(135,div(15,3))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 135+(15/3));
    }

    @Test
    public void testParseAddMult() {
        String input = "mult(1,add(2,3))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 1*(2+3));
    }

    @Test
    public void testParseAddMultDiv() {
        int a=1;
        int b=2;
        int c=3;
        int d=4;
        String input = "mult("+a+",add("+b+",div("+c+","+d+")))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == a*(b+c/d));
    }

    @Test
    public void testParseAddMultDivRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            int c=random.nextInt();
            int d=random.nextInt();
            String input = "mult("+a+",add("+b+",div("+c+","+d+")))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == a*(b+c/d));
        }
    }

    @Test
    public void testLetDiv() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,"+a+",div(a,"+b+"))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (a/b));
        }
    }

    @Test
    public void testLetAndDiv() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,"+a+",div("+b+",a))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (b/a));
        }
    }

    @Test
    public void testLetAndMult() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,"+a+",mult(a,"+b+"))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (a*b));
        }
    }

    @Test
    public void testLetMult() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,"+a+",mult("+b+",a))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (b*a));
        }
    }

    @Test
    public void testLetAddTwice() {
        String input = "let(a,5,add(a,a))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == (10));
    }

    @Test
    public void testMultAddDiv() {
        int a=2, b=2, c=9, d=3;
        String input="mult(add("+a+","+b+"),div("+c+","+d+"))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == (a+b)*(c/d));
    }

    @Test
    public void testMultAddDivRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a = random.nextInt();
            int b = random.nextInt();
            int c = random.nextInt();
            int d = random.nextInt();
            String input = "mult(add(" + a + "," + b + "),div(" + c + "," + d + "))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (a + b) * (c / d));
        }
    }

    @Test
    public void testLetAddTwiceRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            String input = "let(a,"+a+",add(a,a))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (a+a));
        }
    }

    @Test
    public void testDivLetAddLet() {
        int a=3000;
        int b=100;
        int c=20;
        String input = "div("+a+",let(a,"+b+",add(let(a,"+c+",mult(a,30)),a)))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == (a/(c*30+b)));
    }

    @Test
    public void testLetAdd() {
        String input = "let(a,15,add(a,3))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == (15+3));
    }

    @Test
    public void testLetAndAdd() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,"+a+",add("+b+",a))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (b+a));
        }
    }

    @Test
    public void testCombination() {
        String input="let(a, 4, mult(a, add(a, div(add(a, mult(a, 12)), a))))";
        int result=this.parser.parse(input);
        Assert.assertTrue(result == 68);
    }

    @Test
    public void testCombinationRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a, "+a+", mult(a, add(a, div(add(a, mult(a, "+b+")), a))))";
            int result = this.parser.parse(input);
            Assert.assertTrue(result == a*(a+(a+a*b)/a));
        }
    }

    @Test
    public void testLetAndAddAndMultRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            String input = "let(a,"+a+",let(b,mult(10,a),add(b,a)))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (10*a+a));
        }
    }

    @Test
    public void testTwoLetsAndAddRandom() {
        Random random = new Random();
        for (int i = 0; i< RANDOM_TEST_ROUNDS; i++) {
            int a=random.nextInt();
            int b=random.nextInt();
            String input = "let(a,let(b,"+a+",add(b,b)),let(b,"+b+",add(a,b)))";
            int result=this.parser.parse(input);
            Assert.assertTrue(result == (a+a+b));
        }
    }
}
