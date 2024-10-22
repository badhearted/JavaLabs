package org.container;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

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

    @Test
    void testGet() {
        Container<String> container = new Container<>();
        container.add("Hello");
        container.add("World");
        assertEquals("Hello", container.get(0));
        assertEquals("World", container.get(1));
    }

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

    @Test
    void testResizeArray() {
        Container<Integer> container = new Container<>();
        for (int i = 0; i < 20; i++) {
            container.add(i);
        }
        assertEquals(20, container.size());
        assertEquals(19, container.get(19));
    }

    @Test
    void testIndexOutOfBoundsExceptionOnGet() {
        Container<Integer> container = new Container<>();
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(1);
        });
        assertEquals("Index out of bounds: 1", exception.getMessage());
    }

    @Test
    void testIndexOutOfBoundsExceptionOnRemove() {
        Container<Integer> container = new Container<>();
        container.add(10);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(1);
        });
        assertEquals("Index out of bounds: 1", exception.getMessage());
    }

    @Test
    void testRemoveFromEmptyContainer() {
        Container<Integer> container = new Container<>();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(0);
        });
        assertEquals("Index out of bounds: 0", exception.getMessage());
    }

    @Test
    void testGetFromEmptyContainer() {
        Container<Integer> container = new Container<>();
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(0);
        });
        assertEquals("Index out of bounds: 0", exception.getMessage());
    }
}