package org.somepackage;

/**
 * Класс {@code OtherImpl} представляет собой реализацию интерфейса {@link SomeInterface}.
 * Предоставляет конкретную реализацию метода {@code doSomething}.
 */
public class OtherImpl implements SomeInterface {

    /**
     * Выполняет операцию, определенную в интерфейсе {@link SomeInterface}.
     * В данной реализации выводит строку "B" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.println("B");
    }
}
