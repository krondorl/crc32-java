/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

import java.util.Objects;

/**
 * Utility class for calculating CRC-32 checksums.
 *
 * <p>This implementation uses the standard reversed CRC-32 polynomial
 * {@code 0xEDB88320} and returns the checksum as an unsigned 32-bit value
 * represented by a Java {@code long}.</p>
 *
 * <p>This class cannot be instantiated.</p>
 */
public final class Crc32 {

    private static final int CRC32_POLYNOMIAL = 0xEDB88320;
    private static final int[] TABLE = new int[256];

    static {
        for (int i = 0; i < 256; i++) {
            int crc = i;
            for (int j = 0; j < 8; j++) {
                if ((crc & 1) != 0) {
                    crc = (crc >>> 1) ^ CRC32_POLYNOMIAL;
                } else {
                    crc = crc >>> 1;
                }
            }
            TABLE[i] = crc;
        }
    }

    private Crc32() {
        // Utility class
    }

    /**
     * Calculates the CRC-32 checksum of the supplied byte array.
     *
     * @param data the non-null, non-empty data for which the checksum is calculated
     * @return the CRC-32 checksum as an unsigned 32-bit value represented by a {@code long}
     * @throws NullPointerException if {@code data} is {@code null}
     * @throws IllegalArgumentException if {@code data} is empty
     */
    public static long calculateCrc(byte[] data) {
        Objects.requireNonNull(data, "data must not be null");

        if (data.length == 0) {
            throw new IllegalArgumentException("data must not be empty");
        }

        int crc = 0xFFFFFFFF;
        for (int i = 0; i < data.length; i++) {
            crc = (crc >>> 8) ^ TABLE[(crc ^ data[i]) & 0xFF];
        }

        return Integer.toUnsignedLong(crc ^ 0xFFFFFFFF);
    }
}
