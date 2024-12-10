package org.example;

/**
 * Класс, реализующий подразделения
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Person
 * @see FileData
 */
public class Division {
    /**
     * Идентификационный номер подразделения
     */

    private int id;
    /**
     * Название подраздлеления
     */

    private String name;

    /**
     * Конструктор без параметров инициализирует подразделение значениями по умолчанию
     */
    public Division() {
        this.id = 0;
        this.name = "";
    }

    /**
     * Конструктор, инициализирующий подразделение заданным названием
     * @param name название подразделения
     */
    public Division(String name) {
        this.id = 0;
        this.name = name;
    }

    /**
     * Конструктор копирования, создающий новое подразделение из другого подразделения
     * @param other подразделение для копирования
     */
    public Division(Division other) {
        this.id = other.id;
        this.name = other.name;
    }

    /**
     * Метод получения id подразделения
     * @return id подразделения
     */
    public int getId() {
        return this.id;
    }

    /**
     * Метод установки id подразделения
     * @param id идентификационный номер подразделения
     */
    public void setId(int id) {
        if (!isValidDivisionId(id)) {
            throw new IllegalArgumentException("Invalid division id: " + name);
        }
        this.id = id;
    }

    /**
     * Метод получения названия подразделения
     * @return название подразделения
     */
    public String getName() {
        return this.name;
    }

    /**
     * Метод установки названия подразделения
     * @param name название подразделения
     */
    public void setName(String name) {
        if (!isValidDivisionName(name)) {
            throw new IllegalArgumentException("Invalid division name: " + name);
        }
        this.name = name;
    }

    /**
     * Метод преобразования класса к строковому виду
     * @return строковое представление подразделения
     */
    public String toString() {
        return "division id = " + this.id + ", division name = " + this.name;
    }

    /**
     * Метод валидации идентификационного номера подразделения
     * @param id заданный идентификационный номер
     * @return true, если ID не отрицательный, иначе false
     */
    public static boolean isValidDivisionId(int id) {
        return id >= 0;
    }

    /**
     * Метод валидации названия подразделения
     * @param divisionName заданное название подразделения
     * @return true, если название не null и не пустое, иначе false
     */
    public static boolean isValidDivisionName(String divisionName) {
        return divisionName != null && divisionName.length() > 0;
    }
}