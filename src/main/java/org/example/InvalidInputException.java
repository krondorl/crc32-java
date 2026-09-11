/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

public class InvalidInputException extends IllegalArgumentException {
    private final byte[] data;

    public InvalidInputException(byte[] data) {
        super("Invalid input: data must be a byte array.");
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}