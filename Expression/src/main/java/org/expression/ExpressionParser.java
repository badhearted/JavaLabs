package org.expression;

import java.util.*;

/**
 * Класс для парсинга математических выражений и построения абстрактного синтаксического дерева (AST).
 * Поддерживает операции сложения, вычитания, умножения, деления, возведения в степень, а также работу с переменными.
 */
public class ExpressionParser {
    /** Строковое представление математического выражения. */
    private final String expression;

    /** Текущая позиция в выражении. */
    private int pos = -1;

    /** Текущий символ в выражении. */
    private int ch;

    /** Множество переменных, найденных в выражении. */
    private Set<String> variables = new HashSet<>();

    /**
     * Конструктор для создания парсера для заданного математического выражения.
     *
     * @param expression строковое представление математического выражения
     */
    public ExpressionParser(String expression) {
        this.expression = expression;
        nextChar();
    }

    /**
     * Перемещает указатель на следующий символ в выражении.
     */
    private void nextChar() {
        ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
    }

    /**
     * Пропускает символы, пока не встретится указанный символ.
     *
     * @param charToEat символ, который нужно пропустить
     * @return true, если символ был найден и пропущен, иначе false
     */
    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    /**
     * Получить множество переменных, найденных в выражении.
     *
     * @return множество переменных
     */
    public Set<String> getVariables() {
        return variables;
    }

    /**
     * Главный метод для парсинга выражения. Возвращает корень абстрактного синтаксического дерева (AST).
     *
     * @return корень AST, представляющий разобранное выражение
     * @throws RuntimeException если встречается неожиданный символ
     */
    public ExpressionNode parse() {
        ExpressionNode x = parseExpression();
        if (pos < expression.length()) throw new RuntimeException("Неожиданный символ: " + (char) ch);
        return x;
    }

    /**
     * Парсит выражения с операциями сложения и вычитания.
     *
     * @return узел AST, представляющий выражение
     */
    private ExpressionNode parseExpression() {
        ExpressionNode x = parseTerm();
        while (true) {
            if (eat('+')) x = new ExpressionNode('+', x, parseTerm());
            else if (eat('-')) x = new ExpressionNode('-', x, parseTerm());
            else return x;
        }
    }

    /**
     * Парсит выражения с операциями умножения и деления.
     *
     * @return узел AST, представляющий выражение
     */
    private ExpressionNode parseTerm() {
        ExpressionNode x = parseFactor();
        while (true) {
            if (eat('*')) x = new ExpressionNode('*', x, parseFactor());
            else if (eat('/')) x = new ExpressionNode('/', x, parseFactor());
            else return x;
        }
    }

    /**
     * Парсит атомарные элементы: числа, переменные, а также обработку унарных операторов и скобок.
     *
     * @return узел AST, представляющий фактор (число, переменную, выражение в скобках)
     */
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