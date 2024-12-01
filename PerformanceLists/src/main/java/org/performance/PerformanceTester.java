package org.performance;

import java.util.List;

/**
 * Класс PerformanceTester предоставляет методы для измерения и сравнения
 * производительности основных операций в реализациях интерфейса List, таких как ArrayList и LinkedList.
 */
public class PerformanceTester {

    /**
     * Выполняет тесты для указанной реализации List.
     *
     * @param list            Реализация List для тестирования (например, ArrayList или LinkedList).
     * @param operationsCount Количество операций, которое необходимо выполнить для каждого теста.
     */
    public void runTests(List<Integer> list, int operationsCount) {
        // Тестирование методов
        TestResult addResult = testAdd(list, operationsCount);
        TestResult getResult = testGet(list, operationsCount);
        TestResult deleteResult = testDelete(list, operationsCount);

        // Вывод результатов
        printResults(addResult, getResult, deleteResult);
    }

    /**
     * Тестирует производительность операции добавления (add) для указанного списка.
     *
     * @param list            Список для тестирования.
     * @param operationsCount Количество операций добавления.
     * @return Объект TestResult с результатами теста.
     */
    TestResult testAdd(List<Integer> list, int operationsCount) {
        long startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        return new TestResult("add", operationsCount, endTime - startTime);
    }

    /**
     * Тестирует производительность операции получения (get) для указанного списка.
     *
     * @param list            Список для тестирования.
     * @param operationsCount Количество операций получения.
     * @return Объект TestResult с результатами теста.
     */
    TestResult testGet(List<Integer> list, int operationsCount) {
        if (list.isEmpty()) {
            for (int i = 0; i < operationsCount; i++) {
                list.add(i);
            }
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.get(i % list.size());
        }
        long endTime = System.nanoTime();
        return new TestResult("get", operationsCount, endTime - startTime);
    }

    /**
     * Тестирует производительность операции удаления (delete) для указанного списка.
     *
     * @param list            Список для тестирования.
     * @param operationsCount Количество операций удаления.
     * @return Объект TestResult с результатами теста.
     */
    TestResult testDelete(List<Integer> list, int operationsCount) {
        if (list.isEmpty()) {
            for (int i = 0; i < operationsCount; i++) {
                list.add(i);
            }
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.remove(list.size() - 1);
        }
        long endTime = System.nanoTime();
        return new TestResult("delete", operationsCount, endTime - startTime);
    }

    /**
     * Выводит результаты тестов производительности в табличном формате.
     *
     * @param results Массив объектов TestResult с данными тестов.
     */
    private void printResults(TestResult... results) {
        System.out.printf("%-10s %-15s %-15s\n", "Метод", "Операций", "Время (нс)");
        for (TestResult result : results) {
            System.out.printf("%-10s %-15d %-15d\n",
                    result.getMethodName(),
                    result.getOperationsCount(),
                    result.getExecutionTime());
        }
    }
}