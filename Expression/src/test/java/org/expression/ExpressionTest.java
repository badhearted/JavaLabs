public class ExpressionTest {

    // Тестирование простого арифметического выражения: 3 + 5
    @Test
    public void testSimpleExpression() {
        String expression = "3 + 5";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение абстрактного синтаксического дерева (AST)

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());  // Инициализация оценщика с пустым набором переменных
        double result = evaluator.evaluate(ast);  // Оценка выражения

        // Проверка, что результат вычисления равен 8.0 с допуском 0.0001
        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '3 + 5' - 8.0");
    }

    // Тестирование выражения с переменными: 3 + x * (2 - y)
    @Test
    public void testExpressionWithVariables() {
        String expression = "3 + x * (2 - y)";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение AST

        // Заполнение переменных значениями
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 5.0);
        variables.put("y", 1.0);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);  // Инициализация оценщика с переменными
        double result = evaluator.evaluate(ast);  // Оценка выражения

        // Проверка, что результат равен 13.0 (3 + 5 * (2 - 1) = 3 + 5 = 8)
        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '3 + x * (2 - y)' с x=5, y=1 - 13.0");
    }

    // Тестирование выражения с возведением в степень: 2 ^ 3
    @Test
    public void testExpressionWithPower() {
        String expression = "2 ^ 3";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение AST

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());  // Инициализация оценщика с пустым набором переменных
        double result = evaluator.evaluate(ast);  // Оценка выражения

        // Проверка, что результат равен 8.0 (2^3 = 8)
        assertEquals(8.0, result, 0.0001, "Ожидаемый результат для '2 ^ 3' - 8.0");
    }

    // Тестирование выражения с вложенными скобками: 2 * (3 + (4 * 2))
    @Test
    public void testNestedParentheses() {
        String expression = "2 * (3 + (4 * 2))";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение AST

        ExpressionEvaluator evaluator = new ExpressionEvaluator(new HashMap<>());  // Инициализация оценщика
        double result = evaluator.evaluate(ast);  // Оценка выражения

        // Проверка, что результат равен 22.0 (2 * (3 + (4 * 2)) = 2 * (3 + 8) = 2 * 11 = 22)
        assertEquals(22.0, result, 0.0001, "Ожидаемый результат для '2 * (3 + (4 * 2))' - 22.0");
    }

    // Тестирование случая, когда переменная не определена: 5 + z
    @Test
    public void testUndefinedVariable() {
        String expression = "5 + z";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение AST

        Map<String, Double> variables = new HashMap<>();  // Нет значений переменных

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);  // Оценщик с пустыми переменными

        // Проверка, что выбрасывается исключение, так как переменная z не определена
        Exception exception = assertThrows(RuntimeException.class, () -> evaluator.evaluate(ast));
        assertEquals("Переменная z не определена", exception.getMessage());  // Проверка сообщения ошибки
    }

    // Тестирование более сложного выражения с несколькими переменными: ((x + y) * (a - b)) / c
    @Test
    public void testComplexExpression() {
        String expression = "((x + y) * (a - b)) / c";
        ExpressionParser parser = new ExpressionParser(expression);  // Парсинг выражения
        ExpressionNode ast = parser.parse();  // Получение AST

        // Заполнение переменных значениями
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 3.0);
        variables.put("y", 2.0);
        variables.put("a", 10.0);
        variables.put("b", 5.0);
        variables.put("c", 5.0);

        ExpressionEvaluator evaluator = new ExpressionEvaluator(variables);  // Оценщик с переменными
        double result = evaluator.evaluate(ast);  // Оценка выражения

        // Проверка, что результат равен 5.0 ( ((3 + 2) * (10 - 5)) / 5 = (5 * 5) / 5 = 25 / 5 = 5)
        assertEquals(5.0, result, 0.0001, "Ожидаемый результат для '((x + y) * (a - b)) / c' с x=3, y=2, a=10, b=5, c=5 - 5.0");
    }
}