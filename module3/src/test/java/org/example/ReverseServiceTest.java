package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReverseServiceTest {

    private ReverseService reverseService;

    @BeforeEach
    void setUp() {
        reverseService = new ReverseService();
    }

//    @AfterEach
//    void tearDown() {
//        reverseService = null;
//    }

    @Test
    void reverseStringSuccessfullyTest() {
        // Given
        String input = "Hello world";
        String expected = "olleH dlrow";

        // When
        String result = reverseService.reverse(input);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void reverseStringWithSpacesSuccessfullyTest() {
        // Given
        String input = "   Java        21";
        String expected = "avaJ 12";

        // When
        String result = reverseService.reverse(input);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("getStringParams")
    void reverseStringSuccessfullyWithParamsTest(String name,
                                                 String input, String expected) {
        String result = reverseService.reverse(input);

        // Then
        assertNotNull(result);
        assertEquals(expected, result);
    }

    @Test
    void reverseStringWithNullInputTest() {
        assertThrows(IllegalArgumentException.class,
                () -> reverseService.reverse(null));
    }

    @Test
    void reverseStringWithEmptyInputTest() {
        assertThrows(IllegalArgumentException.class,
                () -> reverseService.reverse(""));
    }

    private static Stream<Arguments> getStringParams() {
        return Stream.of(
                Arguments.of("Reverse normal sentence",
                        "Hello world",
                        "olleH dlrow"),
                Arguments.of("Reverse sentence with whitespaces",
                        "   Java        21",
                        "avaJ 12")
        );
    }
}