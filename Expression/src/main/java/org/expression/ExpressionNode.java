package org.expression;

public class ExpressionNode {
    public char operator;
    public ExpressionNode left, right;
    public double value;
    public String variable;

    public ExpressionNode(double value) { // Лист с числовым значением
        this.value = value;
    }

    public ExpressionNode(String variable) { // Лист с переменной
        this.variable = variable;
    }

    public ExpressionNode(char operator, ExpressionNode left, ExpressionNode right) { // Внутренний узел
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public ExpressionNode(int value) {
        this.value = value;
    }
}