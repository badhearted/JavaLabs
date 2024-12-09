package org.getlist;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса Person.
 * Проверка корректности конструктора и методов класса Person.
 */
class PersonTest {

    /**
     * Тестирует конструктор и методы getter класса Person.
     * Проверяет, что значения, переданные в конструктор, корректно сохраняются и возвращаются методами getter.
     */
    @Test
    void testConstructorAndGetters() {
        Division division = new Division(1, "IT");
        Person person = new Person(1, "John Doe", "Male", division, 50000.0, new Date());

        // Проверка значений, переданных в конструктор
        assertEquals(1, person.getId(), "ID должен быть равен 1");
        assertEquals("John Doe", person.getName(), "Имя должно быть 'John Doe'");
        assertEquals("Male", person.getGender(), "Пол должен быть 'Male'");
        assertEquals(division, person.getDivision(), "Подразделение должно быть 'IT'");
        assertEquals(50000.0, person.getSalary(), "Зарплата должна быть 50000.0");
        assertNotNull(person.getBirthDate(), "Дата рождения не должна быть null");
    }

    /**
     * Тестирует метод toString класса Person.
     * Проверяет, что метод toString генерирует строку в ожидаемом формате.
     */
    @Test
    void testToString() {
        Division division = new Division(1, "IT");
        Person person = new Person(1, "John Doe", "Male", division, 50000.0, new Date());

        // Формируем строку с ожиданием для метода toString
        String expectedString = "Person{id=1, name='John Doe', gender='Male', division=IT, salary=50000.0, birthDate=" + person.getBirthDate() + "}";

        // Проверка корректности строки, генерируемой методом toString
        assertEquals(expectedString, person.toString(), "Метод toString должен вернуть правильную строку");
    }
}

/**
 * Тесты для класса Division.
 * Проверка корректности метода toString класса Division.
 */
class DivisionTest {

    /**
     * Тестирует метод toString класса Division.
     * Проверяет, что метод toString генерирует строку в ожидаемом формате.
     */
    @Test
    void testToString() {
        Division division = new Division(1, "IT");

        // Ожидаемая строка для метода toString
        String expectedString = "Division{id=1, name='IT'}";

        // Проверка корректности строки, генерируемой методом toString
        assertEquals(expectedString, division.toString(), "Метод toString должен вернуть правильную строку");
    }
}