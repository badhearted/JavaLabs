package org.container;

/**
 * Контейнер для хранения элементов с динамическим изменением размера.
 * 
 * @param <T> тип элементов, которые будут храниться в контейнере
 */
public class Container<T> {
    private Object[] array; // Массив для хранения элементов
    private int size = 0; // Текущее количество элементов в контейнере
    private static final int INITIAL_CAPACITY = 10; // Начальная емкость контейнера

    /**
     * Конструктор по умолчанию, инициализирующий контейнер с начальной емкостью.
     */
    public Container() {
        array = new Object[INITIAL_CAPACITY];
    }

    /**
     * Возвращает текущее количество элементов в контейнере.
     * 
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Увеличивает размер внутреннего массива в два раза, если он заполнен.
     */
    private void resizeArray() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    /**
     * Выводит все элементы контейнера в консоль.
     */
    public void printContainer() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Добавляет новый элемент в контейнер. При необходимости увеличивает размер массива.
     * 
     * @param element элемент, который нужно добавить
     */
    public void add(T element) {
        if (size == array.length) {
            resizeArray();
        }
        array[size] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     * 
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        return (T) array[index];
    }

    /**
     * Удаляет элемент по указанному индексу и сдвигает последующие элементы влево.
     * 
     * @param index индекс элемента для удаления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вне допустимого диапазона: " + index);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
    }
}