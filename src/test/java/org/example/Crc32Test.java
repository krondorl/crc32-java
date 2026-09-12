/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Crc32Test {

    private String intoHexString(long crcResult) {
        return String.format("0x%x", crcResult);
    }

    @Nested
    @DisplayName("Checksum Tests - Happy Path")
    class HappyPathTests {

        @ParameterizedTest(name = "checks string \"{0}\" to be hex \"{1}\"")
        @CsvSource({
                "123456789, 0xcbf43926",
                "John-Carmack, 0x77ff9cfc",
                "Bjarne-Stroustrup, 0x4c50a184"
        })
        void testCalculateCrc(String text, String expectedHex) {
            byte[] uInt8Array = text.getBytes(StandardCharsets.UTF_8);
            long crcResult = Crc32.calculateCrc(uInt8Array);

            assertEquals(expectedHex, intoHexString(crcResult));
        }
    }

    @Nested
    @DisplayName("Error Handling")
    class ErrorHandlingTests {

        @Test
        @DisplayName("should throw an error for null input")
        void testInvalidInput() {
            // Java prevents non-byte[] types at compile time; testing null input for runtime check
            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> Crc32.calculateCrc(null)
            );

            assertEquals("data must not be null", exception.getMessage());
        }

        @Test
        @DisplayName("should throw an error for an empty array")
        void testEmptyArray() {
            byte[] emptyArray = new byte[0];

            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> Crc32.calculateCrc(emptyArray)
            );

            assertEquals("data must not be empty", exception.getMessage());
        }
    }
}