/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

public class Crc32 {

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

    public static long calculateCrc(byte[] data) {
        if (data == null) {
            throw new InvalidInputException(data);
        }

        if (data.length == 0) {
            throw new EmptyDataArrayException(data);
        }

        int crc = 0xFFFFFFFF;
        for (int i = 0; i < data.length; i++) {
            crc = (crc >>> 8) ^ TABLE[(crc ^ data[i]) & 0xFF];
        }

        return Integer.toUnsignedLong(crc ^ 0xFFFFFFFF);
    }
}
