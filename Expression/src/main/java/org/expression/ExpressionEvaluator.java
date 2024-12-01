package org.expression;

import java.util.Map;

/**
 * Класс для вычисления математических выражений с использованием переменных и операторов.
 * Поддерживает арифметические операции: сложение, вычитание, умножение, деление и возведение в степень.
 */
public class ExpressionEvaluator {
    private final Map<String, Double> variables;

    /**
     * Конструктор для создания объекта, который использует заданный набор переменных.
     *
     * @param variables карта, в которой хранятся переменные и их значения
     */
    public ExpressionEvaluator(Map<String, Double> variables) {
        this.variables = variables;
    }

    /**
     * Оценивает математическое выражение, представленное деревом узлов.
     * Для каждого узла вычисляется его значение в зависимости от типа (переменная, операнд или оператор).
     *
     * @param node корневой узел выражения
     * @return результат вычисления выражения
     * @throws RuntimeException если переменная не определена или оператор неизвестен
     */
    public double evaluate(ExpressionNode node) {
        // Если узел представляет собой переменную
        if (node.variable != null) {
            if (!variables.containsKey(node.variable)) {
                throw new RuntimeException("Переменная " + node.variable + " не определена");
            }
            return variables.get(node.variable);
        }

        // Если узел содержит значение (не оператор)
        if (node.operator == 0) {
            return node.value;
        }

        // Рекурсивное вычисление для левого и правого операнда
        double leftValue = evaluate(node.left);
        double rightValue = evaluate(node.right);

        // Применение оператора
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