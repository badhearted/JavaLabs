package org.somepackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки функциональности класса {@link Injector}.
 * Класс {@link Injector} используется для автоматической инициализации полей объектов
 * в соответствии с конфигурационным файлом.
 */
class InjectorTest {

    private static final String CONFIG_FILE_PATH = "test-config.properties";

    /**
     * Подготовка тестовой среды перед выполнением каждого теста.
     * Создает временный файл конфигурации с заранее заданными значениями.
     *
     * @throws Exception если возникает ошибка при создании файла.
     */
    @BeforeEach
    void setUp() throws Exception {
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            writer.write("org.somepackage.SomeInterface=org.somepackage.SomeImpl\n");
            writer.write("org.somepackage.SomeOtherInterface=org.somepackage.SODoer\n");
        }
    }

    /**
     * Тест успешной инъекции зависимостей в объект.
     * Проверяет, что поля с аннотацией {@link AutoInjectable} корректно инициализируются
     * в соответствии с конфигурационным файлом.
     *
     * @throws Exception если возникает ошибка при инъекции.
     */
    @Test
    void testInjectSuccess() throws Exception {
        Injector injector = new Injector(CONFIG_FILE_PATH);
        SomeBean someBean = new SomeBean();

        injector.inject(someBean);

        assertNotNull(someBean.field1, "Поле field1 должно быть инициализировано");
        assertNotNull(someBean.field2, "Поле field2 должно быть инициализировано");
        assertInstanceOf(SomeImpl.class, someBean.field1, "field1 должен быть экземпляром SomeImpl");
        assertInstanceOf(SODoer.class, someBean.field2, "field2 должен быть экземпляром SODoer");
    }

    /**
     * Тест на обработку ошибки при указании несуществующего класса реализации в конфигурации.
     * Ожидается выброс {@link RuntimeException}.
     *
     * @throws Exception если возникает ошибка при тестировании.
     */
    @Test
    void testInvalidImplementationThrowsException() throws Exception {
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            writer.write("org.somepackage.SomeInterface=org.somepackage.NonExistentClass\n");
        }

        Injector injector = new Injector(CONFIG_FILE_PATH);
        SomeBean someBean = new SomeBean();

        Exception exception = assertThrows(RuntimeException.class, () -> injector.inject(someBean));
        String expectedMessage = "Failed to inject dependency for field";
        assertTrue(exception.getMessage().contains(expectedMessage),
                "Сообщение исключения должно содержать 'Failed to inject dependency for field'");
    }

    /**
     * Тест на игнорирование полей без аннотации {@link AutoInjectable}.
     * Проверяет, что такие поля остаются неинициализированными.
     *
     * @throws Exception если возникает ошибка при тестировании.
     */
    @Test
    void testFieldWithoutAnnotationNotInjected() throws Exception {
        class BeanWithNonAnnotatedField {
            private SomeInterface nonAnnotatedField;
            @AutoInjectable
            private SomeOtherInterface annotatedField;
        }

        Injector injector = new Injector(CONFIG_FILE_PATH);
        BeanWithNonAnnotatedField bean = new BeanWithNonAnnotatedField();

        injector.inject(bean);

        assertNull(bean.nonAnnotatedField, "Поле без аннотации не должно быть инициализировано");
        assertNotNull(bean.annotatedField, "Поле с аннотацией должно быть инициализировано");
        assertInstanceOf(SODoer.class, bean.annotatedField, "annotatedField должен быть экземпляром SODoer");
    }

    /**
     * Тест на обработку отсутствия конфигурации для интерфейса.
     * Проверяет, что выбрасывается {@link RuntimeException}, если для интерфейса
     * в конфигурационном файле не указана реализация.
     *
     * @throws Exception если возникает ошибка при тестировании.
     */
    @Test
    void testMissingConfigurationForInterfaceThrowsException() throws Exception {
        try (FileWriter writer = new FileWriter(CONFIG_FILE_PATH)) {
            writer.write("org.somepackage.SomeInterface=org.somepackage.SomeImpl\n");
        }

        Injector injector = new Injector(CONFIG_FILE_PATH);
        SomeBean someBean = new SomeBean();

        Exception exception = assertThrows(RuntimeException.class, () -> injector.inject(someBean));
        String expectedMessage = "No implementation found for interface: org.somepackage.SomeOtherInterface";
        assertTrue(exception.getMessage().contains(expectedMessage),
                "Сообщение исключения должно содержать 'No implementation found for interface'");
    }
}
