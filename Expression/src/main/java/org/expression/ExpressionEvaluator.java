package org.expression;

import java.util.Map;

public class ExpressionEvaluator {
    private final Map<String, Double> variables;

    public ExpressionEvaluator(Map<String, Double> variables) {
        this.variables = variables;
    }

    public double evaluate(ExpressionNode node) {
        if (node.variable != null) {
            if (!variables.containsKey(node.variable)) {
                throw new RuntimeException("Переменная " + node.variable + " не определена");
            }
            return variables.get(node.variable);
        }

        if (node.operator == 0) {
            return node.value;
        }

        double leftValue = evaluate(node.left);
        double rightValue = evaluate(node.right);

        switch (node.operator) {
            case '+': return leftValue + rightValue;
            case '-': return leftValue - rightValue;
            case '*': return leftValue * rightValue;
            case '/': return leftValue / rightValue;
            case '^': return Math.pow(leftValue, rightValue);
            default: throw new RuntimeException("Неизвестный оператор: " + node.operator);
        }
    }
}