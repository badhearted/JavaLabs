package org.somepackage;

/**
 * Класс {@code SomeImpl} представляет собой реализацию интерфейса {@link SomeInterface}.
 * Предоставляет конкретную реализацию метода {@code doSomething}.
 */
public class SomeImpl implements SomeInterface {

    /**
     * Выполняет операцию, определенную в интерфейсе {@link SomeInterface}.
     * В данной реализации выводит строку "A" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.println("A");
    }
}
