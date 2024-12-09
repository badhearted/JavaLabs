package org.getlist;

import java.util.List;

/**
 * Точка входа в приложение.
 * Этот класс содержит главный метод программы для чтения данных из CSV-файла и вывода информации.
 */
public class Main {

    /**
     * Главный метод программы.
     * Читает данные из CSV-файла и выводит информацию о людях в консоль.
     *
     * @param args аргументы командной строки, не используются в данном примере.
     */
    public static void main(String[] args) {
        String csvFilePath = "foreign_names.csv"; // Укажите путь к CSV файлу
        char separator = ';';

        // Чтение данных из CSV и вывод на экран
        List<Person> people = CsvReaderService.readPeopleFromCsv(csvFilePath, separator);
        people.forEach(System.out::println); // Вывод информации о каждом человеке
    }
}