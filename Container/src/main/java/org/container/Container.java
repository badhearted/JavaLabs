package org.container;

public class Container<T> {
    private Object[] array;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    public Container() {
        array = new Object[INITIAL_CAPACITY];
    }

    public int size() {
        return size;
    }

    private void resizeArray() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public void printContainer() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}