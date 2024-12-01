package org.performance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit-тесты для класса PerformanceTester с увеличенным количеством операций (20000).
 */
class PerformanceTesterTest {

    private static final int OPERATIONS_COUNT = 20000;

    private PerformanceTester performanceTester;
    private List<Integer> arrayList;
    private List<Integer> linkedList;

    @BeforeEach
    void setUp() {
        performanceTester = new PerformanceTester();
        arrayList = new ArrayList<>();
        linkedList = new LinkedList<>();
    }

    @Test
    void testAddArrayList() {
        TestResult result = performanceTester.testAdd(arrayList, OPERATIONS_COUNT);
        assertEquals("add", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
    }

    @Test
    void testAddLinkedList() {
        TestResult result = performanceTester.testAdd(linkedList, OPERATIONS_COUNT);
        assertEquals("add", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
    }

    @Test
    void testGetArrayList() {
        // Предварительно наполняем список
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            arrayList.add(i);
        }
        TestResult result = performanceTester.testGet(arrayList, OPERATIONS_COUNT);
        assertEquals("get", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
    }

    @Test
    void testGetLinkedList() {
        // Предварительно наполняем список
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            linkedList.add(i);
        }
        TestResult result = performanceTester.testGet(linkedList, OPERATIONS_COUNT);
        assertEquals("get", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
    }

    @Test
    void testDeleteArrayList() {
        // Предварительно наполняем список
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            arrayList.add(i);
        }
        TestResult result = performanceTester.testDelete(arrayList, OPERATIONS_COUNT);
        assertEquals("delete", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
        assertTrue(arrayList.isEmpty());
    }

    @Test
    void testDeleteLinkedList() {
        // Предварительно наполняем список
        for (int i = 0; i < OPERATIONS_COUNT; i++) {
            linkedList.add(i);
        }
        TestResult result = performanceTester.testDelete(linkedList, OPERATIONS_COUNT);
        assertEquals("delete", result.getMethodName());
        assertEquals(OPERATIONS_COUNT, result.getOperationsCount());
        assertTrue(result.getExecutionTime() > 0);
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void testEmptyListDelete() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(0));
    }

    @Test
    void testEmptyListGet() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }
}