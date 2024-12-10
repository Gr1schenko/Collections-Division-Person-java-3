package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DivisionTest {

    @Test
    public void testCreateEmptyDivision() {
        Division emptyDivision = new Division();

        assertEquals(0, emptyDivision.getId());
        assertEquals("", emptyDivision.getName());
    }

    @Test
    public void testCreateDivisionByName() {
        Division divisionByName = new Division("A");

        assertEquals(divisionByName.getId(), 0);
        assertEquals("A", divisionByName.getName());
    }

    @Test
    public void testCopyConstructor() {
        Division emptyDivision = new Division();
        Division divisionA = new Division("B");

        emptyDivision = divisionA;

        assertEquals(emptyDivision.getId(), 0);
        assertEquals("B", emptyDivision.getName());
    }

    @Test
    public void testToString() {
        Division division = new Division("C");
        String expectedString = "division id = 0, division name = C";
        assertEquals(expectedString, division.toString());

        division.setId(3);
        expectedString = "division id = 3, division name = C";
        assertEquals(expectedString, division.toString());
    }
}