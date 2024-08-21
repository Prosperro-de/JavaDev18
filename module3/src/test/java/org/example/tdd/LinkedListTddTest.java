package org.example.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTddTest {
    private LinkedListTdd<String> listTdd;

    @BeforeEach
    void setUp() {
        listTdd = new LinkedListTdd<>();
    }


    @Test
    void addElementToListSuccessfulTest() {
        listTdd.add("Hello");

        assertEquals(1, listTdd.size());
    }

    @Test
    void getFirstElementByIndexTest() {
        listTdd.add("Hello");
        listTdd.add("World");

        assertEquals(2, listTdd.size());
        assertEquals("Hello", listTdd.get(0));
        assertEquals("World", listTdd.get(1));
    }

    @Test
    void getElementByInvalidIndexTest() {
        listTdd.add("Hello");
        listTdd.add("World");

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> listTdd.get(-1));
    }
}