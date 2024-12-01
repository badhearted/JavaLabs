package org.performance;

/**
 * Класс TestResult является структурой данных, которая хранит результаты
 * теста производительности: название метода, количество операций и время выполнения.
 */
public class TestResult {
    private final String methodName;
    private final int operationsCount;
    private final long executionTime;

    /**
     * Создает объект TestResult.
     *
     * @param methodName      Название тестируемого метода (например, add, get, delete).
     * @param operationsCount Количество операций, выполненных во время теста.
     * @param executionTime   Время выполнения операций в наносекундах.
     */
    public TestResult(String methodName, int operationsCount, long executionTime) {
        this.methodName = methodName;
        this.operationsCount = operationsCount;
        this.executionTime = executionTime;
    }

    /**
     * Возвращает название тестируемого метода.
     *
     * @return Название метода.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * Возвращает количество операций, выполненных во время теста.
     *
     * @return Количество операций.
     */
    public int getOperationsCount() {
        return operationsCount;
    }

    /**
     * Возвращает время выполнения операций.
     *
     * @return Время выполнения в наносекундах.
     */
    public long getExecutionTime() {
        return executionTime;
    }
}