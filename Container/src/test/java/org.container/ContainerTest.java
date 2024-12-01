package org.container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования функциональности класса {@link Container}.
 */
class ContainerTest {

    /**
     * Проверяет добавление элементов в контейнер и корректность их хранения.
     */
    @Test
    void testAdd() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
        assertEquals(3, container.size());
    }

    /**
     * Проверяет получение элементов из контейнера по индексу.
     */
    @Test
    void testGet() {
        Container<String> container = new Container<>();
        container.add("Hello");
        container.add("World");
        assertEquals("Hello", container.get(0));
        assertEquals("World", container.get(1));
    }

    /**
     * Проверяет удаление элемента из середины контейнера.
     */
    @Test
    void testRemove() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);
        container.remove(1);
        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(30, container.get(1));
    }

    /**
     * Проверяет удаление элемента из начала контейнера.
     */
    @Test
    void testRemoveAtBeginning() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);
        container.remove(0);
        assertEquals(2, container.size());
        assertEquals(20, container.get(0));
        assertEquals(30, container.get(1));
    }

    /**
     * Проверяет удаление элемента с конца контейнера.
     */
    @Test
    void testRemoveAtEnd() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);
        container.remove(2);
        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
    }

    /**
     * Проверяет автоматическое увеличение размера контейнера при добавлении большого количества элементов.
     */
    @Test
    void testResizeArray() {
        Container<Integer> container = new Container<>();
        for (int i = 0; i < 20; i++) {
            container.add(i);
        }
        assertEquals(20, container.size());
        assertEquals(19, container.get(19));
    }

    /**
     * Проверяет выброс исключения при попытке получить элемент по недопустимому индексу.
     */
    @Test
    void testIndexOutOfBoundsExceptionOnGet() {
        Container<Integer> container = new Container<>();
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(1);
        });
        assertEquals("Индекс вне допустимого диапазона: 1", exception.getMessage());
    }

    /**
     * Проверяет выброс исключения при попытке удалить элемент по недопустимому индексу.
     */
    @Test
    void testIndexOutOfBoundsExceptionOnRemove() {
        Container<Integer> container = new Container<>();
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(1);
        });
        assertEquals("Индекс вне допустимого диапазона: 1", exception.getMessage());
    }

    /**
     * Проверяет выброс исключения при попытке удалить элемент из пустого контейнера.
     */
    @Test
    void testRemoveFromEmptyContainer() {
        Container<Integer> container = new Container<>();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(0);
        });
        assertEquals("Индекс вне допустимого диапазона: 0", exception.getMessage());
    }

    /**
     * Проверяет выброс исключения при попытке получить элемент из пустого контейнера.
     */
    @Test
    void testGetFromEmptyContainer() {
        Container<Integer> container = new Container<>();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(0);
        });
        assertEquals("Индекс вне допустимого диапазона: 0", exception.getMessage());
    }
}