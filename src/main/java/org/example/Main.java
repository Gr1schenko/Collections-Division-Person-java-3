package org.example;

import java.util.List;

/**
 * Класс, реализующий работу программы
 * Он читает данные из CSV-файла в ресурсах, содержащего записи людей, обрабатывает их и выводит результаты в консоль
 * @author Yuliya Grischenko
 * @version 1.0-SNAPSHOT
 * @see Division
 * @see Person
 * @see FileData
 */
public class Main {
    /**
     * Метод, который запускает приложение
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        String filename = "foreign_names.csv";
        char separator = ';';

        FileData result = FileData.readPeopleAndDivisionsFromCSV(filename, separator);

        List<Person> people = result.getPeople();
        List<Division> divisions = result.getDivisions();

        System.out.println("Entries in the file: ");
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).toString());
        }

        System.out.println("Divisions in the file: ");
        for (int i = 0; i < divisions.size(); i++) {
            System.out.println(divisions.get(i).toString());
        }

        System.out.println("Number of correct entries in the file = " + people.size());
        System.out.println("Number of different correct divisions = " + divisions.size());
    }
}
