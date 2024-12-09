package org.getlist;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Сервис для чтения данных из CSV файла и создания списка людей.
 */
public class CsvReaderService {

    /**
     * Читает данные о людях из CSV файла.
     *
     * @param csvFilePath Путь к CSV файлу в ресурсах проекта.
     * @param separator Символ-разделитель, используемый в CSV файле.
     * @return Список людей, прочитанных из CSV файла.
     * @throws FileNotFoundException Если указанный файл не найден.
     * @throws IllegalArgumentException Если CSV файл пуст или не имеет заголовков.
     */
    public static List<Person> readPeopleFromCsv(String csvFilePath, char separator) {
        List<Person> people = new ArrayList<>();
        Map<String, Division> divisions = new HashMap<>(); // Карта для уникальных подразделений
        int personId = 1; // ID для людей
        AtomicInteger divisionId = new AtomicInteger(1); // ID для подразделений

        // Формат для преобразования строки в дату (адаптируйте под ваш формат)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        try (InputStream in = CsvReaderService.class.getClassLoader().getResourceAsStream(csvFilePath)) {

            // Проверка, что файл найден
            if (in == null) {
                throw new FileNotFoundException(csvFilePath);
            }

            InputStreamReader inputStreamReader = new InputStreamReader(in);
            CSVReader reader = new CSVReaderBuilder(inputStreamReader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                    .build();

            // Пропускаем заголовок
            String[] nextLine = reader.readNext();
            if (nextLine == null) {
                throw new IllegalArgumentException("CSV файл пуст или отсутствует заголовок.");
            }

            // Используем NumberFormat для обработки чисел
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

            // Чтение данных из файла
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length < 6) continue; // Проверяем, что строка содержит все данные

                    // Извлечение и преобразование данных
                    int id = Integer.parseInt(nextLine[0]);
                    String name = nextLine[1];
                    String gender = nextLine[2];
                    String divisionName = nextLine[4];
                    double salary = numberFormat.parse(nextLine[5]).doubleValue();
                    Date birthDate = dateFormat.parse(nextLine[3]);

                    // Добавляем или получаем подразделение
                    Division division = divisions.computeIfAbsent(divisionName,
                            dn -> new Division(divisionId.getAndIncrement(), dn));

                    // Создаем человека
                    Person person = new Person(id, name, gender, division, salary, birthDate);
                    people.add(person);

                } catch (NumberFormatException | ParseException e) {
                    // Выводим ошибку и продолжаем обработку
                    System.err.println("Ошибка обработки строки: " + Arrays.toString(nextLine) + " - " + e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return people;
    }
}