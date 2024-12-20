package org.somepackage;

/**
 * Класс {@code SODoer} представляет собой реализацию интерфейса {@link SomeOtherInterface}.
 * Предоставляет конкретную реализацию метода {@code doSomeOther}.
 */
public class SODoer implements SomeOtherInterface {

    /**
     * Выполняет операцию, определенную в интерфейсе {@link SomeOtherInterface}.
     * В данной реализации выводит строку "C" в консоль.
     */
    @Override
    public void doSomeOther() {
        System.out.println("C");
    }
}
