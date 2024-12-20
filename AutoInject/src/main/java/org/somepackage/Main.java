package org.somepackage;

/**
 * Главный класс {@code Main}, содержащий точку входа в приложение.
 * В этом классе осуществляется создание экземпляра {@link Injector}, который инъектирует зависимости
 * в объект {@link SomeBean}, после чего выполняется метод {@code foo()} для демонстрации работы инъекции.
 */
public class Main {

    /**
     * Точка входа в приложение.
     * Создает объект {@link Injector}, который инъектирует зависимости в объект {@link SomeBean}.
     * Затем выполняет метод {@code foo()} объекта {@link SomeBean}, который вызывает методы внедренных зависимостей.
     *
     * @param args аргументы командной строки (не используются в данном примере).
     * @throws Exception если возникает ошибка при инъекции зависимостей.
     */
    public static void main(String[] args) throws Exception {
        // Путь к файлу config.properties
        String propertiesFilePath = "config.properties";

        // Создаем экземпляр инъектора с указанным путем к конфигурационному файлу
        Injector injector = new Injector(propertiesFilePath);

        // Инъектируем зависимости в объект SomeBean
        SomeBean sb = injector.inject(new SomeBean());

        // Выполняем метод foo для демонстрации работы инъекции
        sb.foo();
    }
}
