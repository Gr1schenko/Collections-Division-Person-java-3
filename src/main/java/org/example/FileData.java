package org.example;

import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;
import java.io.InputStreamReader;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import java.io.IOException;

/**
 * Класс, реализующий чтение и хранение данных из файла.
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Division
 * @see Person
 */
public class FileData {
    /**
     * Список людей из файла (в списке хранятся объекты класса People)
     */
    private List<Person> people;

    /**
     * Список подразделений людей из файла (в списке хранятся объекты класса Division)
     */
    private List<Division> divisions;

    /**
     * Конструктор без параметров инициализирует данные пустыми списками
     */
    public FileData() {
        this.people = new ArrayList<>();
        this.divisions = new ArrayList<>();
    }

    /**
     * Конструктор, инициализирующий данные заданными параметрами.
     * Является приватным, т.к. его использование предполагается только внутри класса
     * Иначе возможны нарушения целостности данных, такие как добавление дублирующихся подразделений или некорректных связей между людьми и подразделениями
     * @param people список людей
     * @param divisions список подразделений
     */
    private FileData(List<Person> people, List<Division> divisions) {
        this.people = people;
        this.divisions = divisions;
    }

    /**
     * Конструктор копирования, создающий новые данные из старых
     * @param otherFileData заданные данные
     */
    public FileData(FileData otherFileData) {
        this.people = new ArrayList<>(otherFileData.people);
        this.divisions = new ArrayList<>(otherFileData.divisions);
    }

    /**
     * Метод получения списка людей
     * @return список людей
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Метод получения списка подразделений
     * @return список подразделений
     */
    public List<Division> getDivisions() {
        return divisions;
    }

    /**
     * Метод считывания людей и подразделений из CSV файла
     * @param filename имя CSV файла, расположенного в ресурсах
     * @param separator символ, используемый для разделения значений в CSV файле
     * @return объект FileData, содержащий список людей и подразделений из файла
     */
    public static FileData readPeopleAndDivisionsFromCSV(String filename, char separator) {
        List<Person> people = new ArrayList<>();
        List<Division> divisions = new ArrayList<>();
        List<String> divisionNames = new ArrayList<>();
        int divisionIdCounter = 1;

        InputStream in = FileData.class.getClassLoader().getResourceAsStream(filename);

        if (in == null) {
            System.out.println("File not found: " + filename);
            return new FileData(people, divisions);
        }

        try (CSVReader reader  = new CSVReaderBuilder(new InputStreamReader(in))
                .withSkipLines(1)
                .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                .build()) {

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if ((nextLine.length != 6)) {
                    System.out.println("Skipping line due to insufficient data: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }

                String idStr = nextLine[0];
                String name = nextLine[1];
                String gender = nextLine[2];
                String birthDate = nextLine[3];
                String divisionName = nextLine[4];
                String salaryStr = nextLine[5];

                if (idStr.isEmpty() || name.isEmpty() || gender.isEmpty() || birthDate.isEmpty() || divisionName.isEmpty() || salaryStr.isEmpty()) {
                    System.out.println("Skipping line due to empty fields: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }

                int id;
                int salary;
                try {
                    id = Integer.parseInt(idStr);
                    salary = Integer.parseInt(salaryStr);
                }
                catch (NumberFormatException e) {
                    System.out.println("Skipping line due to invalid number format: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }

                if (!Person.isValidId(id)) {
                    System.out.println("Error. Invalid ID " + id + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }
                if (!Person.isValidName(name)) {
                    System.out.println("Error. Incorrect name " + name + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }
                if (!Person.isValidGender(gender)) {
                    System.out.println("Error. Incorrect gender " + gender + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }
                if (!Division.isValidDivisionName(divisionName)) {
                    System.out.println("Error. Incorrect department name " + divisionName + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }
                if (!Person.isValidSalary(salary)) {
                    System.out.println("Error. Incorrect salary " + salary + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }
                if (!Person.isValidBirthDate(birthDate)) {
                    System.out.println("Error. Incorrect date of birth " + birthDate + " in line: ");
                    for (String value : nextLine) {
                        System.out.print(value + ";");
                    }
                    System.out.println();
                    continue;
                }

                Division division = null;
                int existingDivisionIndex = divisionNames.indexOf(divisionName);

                if (existingDivisionIndex == -1) {
                    division = new Division(divisionName);
                    division.setId(divisionIdCounter++);
                    divisions.add(division);
                    divisionNames.add(divisionName);
                }
                else {
                    division = divisions.get(existingDivisionIndex);
                }

                if (division != null) {
                    Person person = new Person(id, name, gender, division, salary, birthDate);
                    people.add(person);
                }
            }
        }
        catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
            System.out.println();
        }
        catch (com.opencsv.exceptions.CsvValidationException e) {
            System.out.println("CSV validation error: " + e.getMessage());
            System.out.println();
        }
        return new FileData(people, divisions);
    }
}