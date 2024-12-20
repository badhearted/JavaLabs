package org.somepackage;

import org.somepackage.AutoInjectable;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс {@code Injector} отвечает за инъекцию зависимостей в объекты с аннотацией {@link AutoInjectable}.
 * Он загружает конфигурационный файл, в котором указаны соответствия между интерфейсами и их реализациями.
 * В процессе работы класса создаются экземпляры реализаций и внедряются в соответствующие поля объектов.
 */
public class Injector {

    /** Объект для хранения свойств, загруженных из конфигурационного файла. */
    private final Properties properties = new Properties();

    /**
     * Конструктор класса {@code Injector}.
     * Загружает конфигурационный файл, в котором содержатся данные о соответствиях интерфейсов и их реализаций.
     *
     * @param propertiesFilePath путь к конфигурационному файлу.
     * @throws Exception если возникает ошибка при загрузке файла настроек.
     */
    public Injector(String propertiesFilePath) throws Exception {
        // Загружаем файл настроек
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        }
    }

    /**
     * Метод для инъекции зависимостей в объект.
     * Проходит по всем полям объекта и проверяет наличие аннотации {@link AutoInjectable}.
     * Если аннотация присутствует, пытается найти соответствующую реализацию в конфигурационном файле и инъектировать ее.
     *
     * @param <T> тип объекта, в который выполняется инъекция.
     * @param obj объект, в который будут инъектированы зависимости.
     * @return объект с инъектированными зависимостями.
     * @throws RuntimeException если не удается инъектировать зависимость или найти реализацию.
     */
    public <T> T inject(T obj) {
        Class<?> objClass = obj.getClass();
        // Проходим по всем полям класса
        for (Field field : objClass.getDeclaredFields()) {
            // Если поле помечено аннотацией AutoInjectable
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                // Получаем имя интерфейса, которое соответствует типу поля
                String interfaceName = field.getType().getName();
                // Получаем имя класса реализации из конфигурационного файла
                String implementationClassName = properties.getProperty(interfaceName);

                if (implementationClassName != null) {
                    try {
                        // Создаем экземпляр класса реализации
                        Class<?> implementationClass = Class.forName(implementationClassName);
                        Object implementationInstance = implementationClass.getDeclaredConstructor().newInstance();
                        // Делаем поле доступным и инъектируем зависимость
                        field.setAccessible(true);
                        field.set(obj, implementationInstance);
                    } catch (Exception e) {
                        // Если произошла ошибка при инъекции, выбрасываем исключение
                        throw new RuntimeException("Failed to inject dependency for field: " + field.getName(), e);
                    }
                } else {
                    // Если в конфигурации не найдено соответствия для интерфейса, выбрасываем исключение
                    throw new RuntimeException("No implementation found for interface: " + interfaceName);
                }
            }
        }
        return obj;
    }
}
