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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

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
            byte[] inputBytes = text.getBytes(StandardCharsets.UTF_8);
            long crcResult = Crc32.calculateCrc(inputBytes);

            assertEquals(expectedHex, intoHexString(crcResult));
        }
    }

    @Test
    void calculateCrcByteArrayThrowsWhenDataIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Crc32.calculateCrc((byte[]) null)
        );

        assertEquals("data must not be null", exception.getMessage());
    }

    @Test
    void calculateCrcInputStreamThrowsWhenInputStreamIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Crc32.calculateCrc((InputStream) null)
        );

        assertEquals("inputStream must not be null", exception.getMessage());
    }

    @Test
    void calculateCrcPathThrowsWhenPathIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Crc32.calculateCrc((Path) null)
        );

        assertEquals("path must not be null", exception.getMessage());
    }

    @Test
    void calculateCrcFileThrowsWhenFileIsNull() {
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> Crc32.calculateCrc((File) null)
        );

        assertEquals("file must not be null", exception.getMessage());
    }

    @Test
    void calculateCrcInputStreamPropagatesIOException() {
        InputStream failingInputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Test read failure");
            }

            @Override
            public int read(byte[] buffer) throws IOException {
                throw new IOException("Test read failure");
            }
        };

        IOException exception = assertThrows(
                IOException.class,
                () -> Crc32.calculateCrc(failingInputStream)
        );

        assertEquals("Test read failure", exception.getMessage());
    }

    @Test
    void calculateCrcPathThrowsWhenFileDoesNotExist() {
        Path path = Path.of(
                "this-file-should-not-exist-1234567890.bin"
        );

        assertThrows(
                IOException.class,
                () -> Crc32.calculateCrc(path)
        );
    }

    @Test
    void calculateCrcFileThrowsWhenFileDoesNotExist() {
        File file = new File(
                "this-file-should-not-exist-1234567890.bin"
        );

        assertThrows(
                IOException.class,
                () -> Crc32.calculateCrc(file)
        );
    }
}