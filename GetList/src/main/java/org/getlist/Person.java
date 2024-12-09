package org.getlist;

import java.util.Date;

/**
 * Класс, представляющий человека в системе.
 * Хранит информацию о человеке, такую как ID, имя, пол, подразделение, зарплату и дату рождения.
 */
public class Person {

    private int id; // Уникальный идентификатор человека
    private String name; // Имя человека
    private String gender; // Пол человека
    private Division division; // Подразделение, к которому принадлежит человек
    private double salary; // Зарплата человека
    private Date birthDate; // Дата рождения человека

    /**
     * Конструктор для создания объекта "Человек".
     *
     * @param id уникальный идентификатор человека
     * @param name имя человека
     * @param gender пол человека
     * @param division подразделение, к которому принадлежит человек
     * @param salary зарплата человека
     * @param birthDate дата рождения человека
     */
    public Person(int id, String name, String gender, Division division, double salary, Date birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    // Геттеры для доступа к полям объекта
    /**
     * Получает уникальный идентификатор человека.
     *
     * @return уникальный идентификатор человека
     */
    public int getId() {
        return id;
    }

    /**
     * Получает имя человека.
     *
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * Получает пол человека.
     *
     * @return пол человека
     */
    public String getGender() {
        return gender;
    }

    /**
     * Получает подразделение, к которому принадлежит человек.
     *
     * @return подразделение человека
     */
    public Division getDivision() {
        return division;
    }

    /**
     * Получает зарплату человека.
     *
     * @return зарплата человека
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Получает дату рождения человека.
     *
     * @return дата рождения человека
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Переопределенный метод toString для представления объекта "Человек" в виде строки.
     * Строка будет включать информацию о человеке, такую как ID, имя, пол, подразделение, зарплату и дату рождения.
     *
     * @return строковое представление объекта "Человек"
     */
    @Override
    public String toString() {
        return "Person{id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", division=" + (division != null ? division.getName() : "N/A") +
                ", salary=" + salary +
                ", birthDate=" + birthDate + '}';
    }
}