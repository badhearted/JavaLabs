package org.container;

/**
 * Главный класс для демонстрации использования класса {@link Container}.
 * В этом классе выполняются базовые операции, такие как добавление, удаление и получение элементов
 * из пользовательского контейнера, а также отображение результатов работы.
 */
public class ContainerMain {

    /**
     * Основной метод для запуска программы.
     *
     * @param args аргументы командной строки (в данной программе не используются)
     */
    public static void main(String[] args) {
        // Создание контейнера для элементов типа Integer
        Container<Integer> container = new Container<>();

        // Добавление элементов в контейнер
        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);

        // Вывод содержимого контейнера после добавления элементов
        System.out.println("Контейнер после добавления элементов:");
        container.printContainer();

        // Вывод количества элементов в контейнере
        System.out.println("Количество элементов в контейнере: " + container.size());

        // Получение и вывод элемента по индексу 2
        System.out.println("Элемент с индексом 2: " + container.get(2));

        // Удаление элемента с индексом 2
        container.remove(2);

        // Вывод содержимого контейнера после удаления элемента
        System.out.println("Контейнер после удаления элемента:");
        container.printContainer();

        // Вывод обновленного количества элементов в контейнере
        System.out.println("Количество элементов в контейнере: " + container.size());
    }
}