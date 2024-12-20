package org.somepackage;

/**
 * Класс {@code SomeBean} представляет собой объект, в который автоматически внедряются зависимости.
 * Поля {@code field1} и {@code field2} инициализируются с помощью аннотации {@link AutoInjectable}.
 */
public class SomeBean {

    /** Поле для внедрения реализации интерфейса {@link SomeInterface}. */
    @AutoInjectable
    SomeInterface field1;

    /** Поле для внедрения реализации интерфейса {@link SomeOtherInterface}. */
    @AutoInjectable
    SomeOtherInterface field2;

    /**
     * Выполняет операции, используя внедренные зависимости.
     * Вызывает методы {@code doSomething} и {@code doSomeOther}.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
