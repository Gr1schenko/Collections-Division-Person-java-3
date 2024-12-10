package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


class FileDataTest {

    @Test
    public void testReadFromCorrectFile() {
        String filename = "foreign_names.csv";
        char separator = ';';

        FileData fileData = FileData.readPeopleAndDivisionsFromCSV(filename, separator);

        List<Person> people = fileData.getPeople();
        List<Division> divisions = fileData.getDivisions();

        assertFalse(people.isEmpty());
        assertFalse(divisions.isEmpty());
    }

    @Test
    public void testReadFromInvalidFile() {
        String filename = "invalid_file.csv";
        char separator = ';';

        FileData fileData = FileData.readPeopleAndDivisionsFromCSV(filename, separator);
        List<Person> people = fileData.getPeople();
        List<Division> divisions = fileData.getDivisions();

        assertEquals(3, people.size());
        assertEquals(2, divisions.size());
    }

    @Test
    public void testReadFromNonExistentFile() {
        String filename = "non_existent_file.csv";
        char separator = ';';

        FileData fileData = FileData.readPeopleAndDivisionsFromCSV(filename, separator);
        List<Person> people = fileData.getPeople();
        List<Division> divisions = fileData.getDivisions();

        assertTrue(people.isEmpty());
        assertTrue(divisions.isEmpty());
    }
}