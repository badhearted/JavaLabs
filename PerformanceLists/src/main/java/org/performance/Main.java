package org.performance;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс Main является точкой входа в приложение.
 * Выполняет тестирование производительности ArrayList и LinkedList,
 * сравнивая время выполнения основных операций: добавления, получения и удаления элементов.
 */
public class Main {

    /**
     * Основной метод запускает тестирование производительности для ArrayList и LinkedList.
     *
     * @param args Аргументы командной строки (в данном приложении не используются).
     */
    public static void main(String[] args) {
        // Количество операций для тестирования
        int operationsCount = 20000;

        // Создание экземпляра тестера
        PerformanceTester tester = new PerformanceTester();

        // Тест производительности ArrayList
        System.out.println("Тест производительности ArrayList:");
        tester.runTests(new ArrayList<>(), operationsCount);

        // Тест производительности LinkedList
        System.out.println("\nТест производительности LinkedList:");
        tester.runTests(new LinkedList<>(), operationsCount);
    }
}