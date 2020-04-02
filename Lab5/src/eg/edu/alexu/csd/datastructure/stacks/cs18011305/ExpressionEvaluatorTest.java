package eg.edu.alexu.csd.datastructure.stacks.cs18011305;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ExpressionEvaluatorTest {
    @Test
    public void infixToPostixTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        String postFix = evaluator.infixToPostfix("2 + 3 * 4");
        assertEquals("2 3 4 * +", postFix);
        postFix = evaluator.infixToPostfix("a * b + 5");
        assertEquals("a b * 5 +", postFix);
        postFix = evaluator.infixToPostfix("(1 + 2) * 7");
        assertEquals("1 2 + 7 *", postFix);
        postFix = evaluator.infixToPostfix("a * b / c");
        assertEquals("a b * c /", postFix);
        postFix = evaluator.infixToPostfix("(a / (b - c + d)) * (e - a) * c");
        assertEquals("a b c - d + / e a - * c *", postFix);
        postFix = evaluator.infixToPostfix("a / b - c + d * e - a * c");
        assertEquals("a b / c - d e * + a c * -", postFix);
        postFix = evaluator.infixToPostfix("20 + 10");
        assertEquals("20 10 +", postFix);
        postFix = evaluator.infixToPostfix("5 * -3");
        assertEquals("5 0 3 - *", postFix);
        postFix = evaluator.infixToPostfix("50 * -30");
        assertEquals("50 0 30 - *", postFix);
    }
    @Test
    public void evaluateTest(){
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        int res = evaluator.evaluate("2 4 *");
        assertEquals(8, res);
        res = evaluator.evaluate("5 8 + 3 -");
        assertEquals(10, res);
        res = evaluator.evaluate("2 3 4 * +");
        assertEquals(14, res);
        res = evaluator.evaluate("20 10 *");
        assertEquals(200, res);
        res = evaluator.evaluate("5 0 3 - *");
        assertEquals(-15, res);
        res = evaluator.evaluate("200 10 *");
        assertEquals(2000, res);
        res = evaluator.evaluate("020 10 *");
        assertEquals(200, res);
        res = evaluator.evaluate("20 5 * 5 /");
        assertEquals(20, res);
        res = evaluator.evaluate("50 0 30 - *");
        assertEquals(-1500, res);
    }
}
