package org.expression;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class ExpressionTest {

    @Test
    public void testSimpleExpression() {
        String expression = "3 + 5";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());
        double result = evaluator.evaluate(ast);

        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '3 + 5' - 8.0");
    }

    @Test
    public void testExpressionWithVariables() {
        String expression = "3 + x * (2 - y)";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        variables.put("y", 1.0);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);

        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '3 + x * (2 - y)' с x=5, y=1 - 13.0");
    }

    @Test
    public void testExpressionWithPower() {
        String expression = "2 ^ 3";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());
        double result = evaluator.evaluate(ast);

        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '2 ^ 3' - 8.0");
    }

    @Test
    public void testNestedParentheses() {
        String expression = "2 * (3 + (4 * 2))";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());
        double result = evaluator.evaluate(ast);

        assertEquals(22.0, result, 0.0001, "Ожидаемый результат для '2 * (3 + (4 * 2))' - 22.0");
    }

    @Test
    public void testUndefinedVariable() {
        String expression = "5 + z";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);

        Exception exception = assertThrows(RuntimeException.class, () -> evaluator.evaluate(ast));
        assertEquals("Переменная z не определена", exception.getMessage());
    }

    @Test
    public void testComplexExpression() {
        String expression = "((x + y) * (a - b)) / c";
        ExpressionParser parser = new ExpressionParser(expression);
        ExpressionNode ast = parser.parse();

        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 2.0);
        variables.put("a", 10.0);
        variables.put("b", 5.0);
        variables.put("c", 5.0);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
        double result = evaluator.evaluate(ast);

        assertEquals(5.0, result, 0.0001, "Ожидаемый результат для '((x + y) * (a - b)) / c' с x=3, y=2, a=10, b=5, c=5 - 5.0");
    }
}