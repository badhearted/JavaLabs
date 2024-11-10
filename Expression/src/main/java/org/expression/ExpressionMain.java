package org.expression;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExpressionMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();

        ExpressionParser parser = new ExpressionParser(expression);

        try {
            ExpressionNode ast = parser.parse(); // Разбор выражения в AST

            // Запрашиваем значения переменных у пользователя
            Map<String, Double> variables = new HashMap<>();
            for (String var : parser.getVariables()) {
                System.out.print("Введите значение для " + var + ": ");
                variables.put(var, scanner.nextDouble());
            }

            ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);
            double result = evaluator.evaluate(ast); // Вычисление значения AST
            System.out.println("Результат: " + result);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}