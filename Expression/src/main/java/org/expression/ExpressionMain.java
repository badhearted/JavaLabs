package org.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Главный класс для ввода, парсинга и вычисления математического выражения.
 * Пользователь вводит выражение, после чего программа парсит его, запрашивает значения переменных
 * и вычисляет результат.
 */
public class ExpressionMain {
    
    /**
     * Точка входа в программу.
     * Запрашивает у пользователя ввод математического выражения, парсит его в абстрактное синтаксическое дерево (AST),
     * затем запрашивает значения переменных и вычисляет результат выражения.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();

        ExpressionParser parser = new ExpressionParser(expression);

        try {
            // Разбор выражения в AST
            ExpressionNode ast = parser.parse();

            // Запрашиваем значения переменных у пользователя
            Map<String, Double> variables = new HashMap<>();
            for (String var : parser.getVariables()) {
                System.out.print("Введите значение для " + var + ": ");
                variables.put(var, scanner.nextDouble());
            }

            // Создаем объект для вычисления и вычисляем результат
            ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
            double result = evaluator.evaluate(ast);
            System.out.println("Результат: " + result);

        } catch (Exception e) {
            // Обработка ошибок
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}