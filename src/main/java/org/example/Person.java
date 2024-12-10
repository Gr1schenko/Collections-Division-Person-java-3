package org.example;

/**
 * Класс, реализующий запись человека
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Division
 * @see FileData
 */
public class Person {
    /**
     * Идентификационный номер человека
     */
    private int id;

    /**
     * Имя человека
     */
    private String name;

    /**
     * Пол человека
     */
    private String gender;

    /**
     * Подразделение человка (объект класса Division)
     */
    private Division division;

    /**
     * Зарплата человека
     */
    private int salary;

    /**
     * Дата рождения человека
     */
    private String birthDate;

    /**
     * Конструктор без параметров инициализирует запись человека значениями по умолчанию
     */
    public Person() {
        this.id = 0;
        this.name = "";
        this.gender = "";
        this.division = new Division();
        this.salary = 0;
        this.birthDate = "";
    }

    /**
     * Конструктор, инициализирующий запись человека заданными параметрами, один из которых объект класса Division
     * @param id идентификационный номер
     * @param name имя человека
     * @param gender пол человека
     * @param division подразделение человека
     * @param salary зарплата человека
     * @param birthDate дата рождения человека
     */
    public Person(int id, String name, String gender, Division division, int salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Конструктор, инициализирующий человека заданными параметрами, один из которых название подразделения
     * @param id заданный идентификационный номер
     * @param name заданное имя человека
     * @param gender заданный пол человека
     * @param divisionName заданное название подразделения человека
     * @param salary зарплата человека
     * @param birthDate ата рождения человека
     */
    public Person(int id, String name, String gender, String divisionName, int salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = new Division(divisionName);
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Конструктор копирования, создающий зновую запись о человеке из другой записи
     * @param other заданная запись для копирования
     */
    public Person(Person other) {
        this.id = other.id;
        this.name = other.name;
        this.gender = other.gender;
        this.division = new Division(other.division);
        this.salary = other.salary;
        this.birthDate = other.birthDate;
    }

    /**
     * Метод получения id записи человека
     * @return id записи человека
     */
    public int getId() {
        return this.id;
    }

    /**
     * Метод установки id записи человека
     * @param id заданный идентификационный номер
     */
    public void setId(int id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }
        this.id = id;
    }

    /**
     * Метод получения имени человека
     * @return имя человека
     */
    public String getName() {
        return this.name;
    }

    /**
     * Метод установки имени человека
     * @param name заданное имя
     */
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
        this.name = name;
    }

    /**
     * Метод получения пола человека
     * @return пол человека
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Метод установки пола человека
     * @param gender заданный пол
     */
    public void setGender(String gender) {
        if (!isValidGender(gender)) {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }
        this.gender = gender;
    }

    /**
     * Метод получения подразделения человека
     * @return подразделение человека
     */
    public Division getDivision() {
        return this.division;
    }

    /**
     * Метод установки подразделения человека
     * @param division заданное подразделение
     */
    public void setDivision(Division division) {
        if (!isValidDivision(division)) {
            throw new IllegalArgumentException("Invalid division: " + division.toString());
        }
        this.division = division;
    }

    /**
     * Метод получения зарплаты человека
     * @return зарплата человека
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Метод установки зарплаты человека
     * @param salary заданная зарплата
     */
    public void setSalary(int salary) {
        if (!isValidSalary(salary)) {
            throw new IllegalArgumentException("Invalid salary: " + salary);
        }
        this.salary = salary;
    }

    /**
     * Метод получения даты рождения человека
     * @return дата рождения человека
     */
    public String getBirthDate() {
        return this.birthDate;
    }

    /**
     * Метод установки даты рождения человека
     * @param birthDate заданная дата рождения
     */
    public void setBirthDate(String birthDate) {
        if (!isValidBirthDate(birthDate)) {
            throw new IllegalArgumentException("Invalid birth date: " + birthDate);
        }
        this.birthDate = birthDate;
    }

    /**
     * Метод валидации идентификационного номера
     * @param id заданный идентификационный номер
     * @return true, если ID не отрицательный, иначе false
     */
    public static boolean isValidId(int id) {
        return id >= 0;
    }

    /**
     * Метод валидации имени
     * @param name заданное имя
     * @return true, если имя не null и не пустое, иначе false
     */
    public static boolean isValidName(String name) {
        return name != null && name.length() > 0;
    }

    /**
     * Метод валидации пола
     * @param gender заданный пол
     * @return true, если пол не null и не пустой, иначе false
     */
    public static boolean isValidGender(String gender) {
        return gender != null && (gender.equals("male") || gender.equals("Male") || gender.equals("female") || gender.equals("Female"));
    }


    /**
     * Метод валидации подразделения
     * @param division заданное подразделение
     * @return true, если подразделение не null и проходит валидацию в своем классе
     */
    public static boolean isValidDivision(Division division) {
        return division != null && Division.isValidDivisionId(division.getId()) && Division.isValidDivisionName(division.getName());
    }

    /**
     * Метод валидации зарплаты
     * @param salary заданная зарплата
     * @return true, если зарплата не отрицательная, иначе false
     */
    public static boolean isValidSalary(int salary) {
        return salary >= 0;
    }

    /**
     * Метод валидации даты рождения
     * @param birthDate заданная дата рождения
     * @return true, если дата рождения не null и соответствует формату DD.MM.YYYY, иначе false
     */
    public static boolean isValidBirthDate(String birthDate) {
        return birthDate != null && birthDate.length() == 10;
    }

    /**
     * Метод преобразования сущности к строковому виду
     * @return строковое представление записи человека
     */
    public String toString() {
        return "id = " + this.id + ", name = " + this.name
                + ", gender = " + this.gender + ", " + division.toString()
                + ", salary = " + this.salary + ", birthDate = " + this.birthDate;
    }
}