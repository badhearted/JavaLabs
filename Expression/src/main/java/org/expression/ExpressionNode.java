package org.expression;

/**
 * Класс, представляющий узел абстрактного синтаксического дерева (AST) выражения.
 * Каждый узел может быть либо операцией, либо значением (числом или переменной).
 */
public class ExpressionNode {
    
    /** Оператор, который применен к узлам. */
    public char operator;

    /** Левый дочерний узел. */
    public ExpressionNode left;

    /** Правый дочерний узел. */
    public ExpressionNode right;

    /** Значение узла, если это число. */
    public double value;

    /** Переменная, если это переменная в выражении. */
    public String variable;

    /**
     * Конструктор для создания узла, представляющего числовое значение.
     *
     * @param value числовое значение
     */
    public ExpressionNode(double value) {
        this.value = value;
    }

    /**
     * Конструктор для создания узла, представляющего переменную.
     *
     * @param variable имя переменной
     */
    public ExpressionNode(String variable) {
        this.variable = variable;
    }

    /**
     * Конструктор для создания внутреннего узла, который представляет операцию.
     * Узел содержит оператор и два дочерних узла (левый и правый).
     *
     * @param operator оператор для выполнения операции
     * @param left левый дочерний узел
     * @param right правый дочерний узел
     */
    public ExpressionNode(char operator, ExpressionNode left, ExpressionNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    /**
     * Конструктор для создания узла, представляющего целочисленное значение.
     *
     * @param value целочисленное значение
     */
    public ExpressionNode(int value) {
        this.value = value;
    }
}