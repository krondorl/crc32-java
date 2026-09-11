/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

public class EmptyDataArrayException extends IllegalArgumentException {
    private final byte[] data;

    public EmptyDataArrayException(byte[] data) {
        super("Cannot calculate CRC for an empty array (possible empty String given).");
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}