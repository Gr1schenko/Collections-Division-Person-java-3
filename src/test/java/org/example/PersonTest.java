package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PersonTest {

    @Test
    public void testPersonCreation() {
        Division division = new Division("A");
        Person person = new Person(1, "Ann", "Female", division, 50000, "01.01.1990");
        assertNotNull(person);
        assertEquals(1, person.getId());
        assertEquals("Ann", person.getName());
        assertEquals("Female", person.getGender());
        assertEquals(0, person.getDivision().getId());
        assertEquals("A", person.getDivision().getName());
        assertEquals(50000, person.getSalary());
        assertEquals("01.01.1990", person.getBirthDate());
    }

    @Test
    public void testSettersAndGetters() {
        Division division = new Division("B");
        Person person = new Person(3, "Billie", "Male", division, 70000, "02.02.1992");

        person.setName("Billie");
        assertEquals("Billie", person.getName());

        person.setGender("Female");
        assertEquals("Female", person.getGender());

        Division newDivision = new Division("D");
        person.setDivision(newDivision);
        assertEquals(0, person.getDivision().getId());
        assertEquals("D", person.getDivision().getName());

        person.setSalary(80000);
        assertEquals(80000, person.getSalary());

        person.setBirthDate("03.03.1993");
        assertEquals("03.03.1993", person.getBirthDate());
    }

    @Test
    public void testToString() {
        Division division = new Division("C");
        Person person = new Person(4, "Charlie", "Female", division, 90000, "04.04.1994");
        String expectedString = "id = 4, name = Charlie, gender = Female, division id = 0, division name = C, salary = 90000, birthDate = 04.04.1994";
        assertEquals(expectedString, person.toString());
    }
}