package org.expression;

import java.util.*;

public class ExpressionParser {
    private final String expression;
    private int pos = -1;
    private int ch;
    private Set<String> variables = new HashSet<>();

    public ExpressionParser(String expression) {
        this.expression = expression;
        nextChar();
    }

    private void nextChar() {
        ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public Set<String> getVariables() {
        return variables;
    }

    public ExpressionNode parse() {
        ExpressionNode x = parseExpression();
        if (pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char) ch);
        return x;
    }

    private ExpressionNode parseExpression() {
        ExpressionNode x = parseTerm();
        while (true) {
            if (eat('+')) x = new ExpressionNode('+', x, parseTerm());
            else if (eat('-')) x = new ExpressionNode('-', x, parseTerm());
            else return x;
        }
    }

    private ExpressionNode parseTerm() {
        ExpressionNode x = parseFactor();
        while (true) {
            if (eat('*')) x = new ExpressionNode('*', x, parseFactor());
            else if (eat('/')) x = new ExpressionNode('/', x, parseFactor());
            else return x;
        }
    }

    private ExpressionNode parseFactor() {
        if (eat('+')) return parseFactor(); // унарный плюс
        if (eat('-')) return new ExpressionNode('-', new ExpressionNode(0), parseFactor()); // унарный минус

        ExpressionNode x;
        int startPos = this.pos;
        if (eat('(')) {
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') {
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            double value = Double.parseDouble(expression.substring(startPos, this.pos));
            x = new ExpressionNode(value);
        } else if (ch >= 'a' && ch <= 'z') { // Переменная или функция
            while (ch >= 'a' && ch <= 'z') nextChar();
            String name = expression.substring(startPos, this.pos);
            variables.add(name);
            x = new ExpressionNode(name);
        } else {
            throw new RuntimeException("Неожиданный символ: " + (char) ch);
        }

        if (eat('^')) x = new ExpressionNode('^', x, parseFactor()); // степень
        return x;
    }
}