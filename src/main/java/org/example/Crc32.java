/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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

    public static long calculateCrc(byte[] data) {
        Objects.requireNonNull(data, "data must not be null");

        int crc = 0xFFFFFFFF;

        crc = updateCrc(crc, data, 0, data.length);

        return Integer.toUnsignedLong(crc ^ 0xFFFFFFFF);
    }

    private static int updateCrc(int crc, byte[] data, int offset, int length) {
        for (int i = offset; i < offset + length; i++) {
            crc = (crc >>> 8) ^ TABLE[(crc ^ data[i]) & 0xFF];
        }

        return crc;
    }

    public static long calculateCrc(InputStream inputStream) throws IOException {
        Objects.requireNonNull(inputStream, "inputStream must not be null");

        byte[] buffer = new byte[8192];
        int crc = 0xFFFFFFFF;

        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            crc = updateCrc(crc, buffer, 0, bytesRead);
        }

        return Integer.toUnsignedLong(crc ^ 0xFFFFFFFF);
    }

    public static long calculateCrc(Path path) throws IOException {
        Objects.requireNonNull(path, "path must not be null");

        try (InputStream inputStream = Files.newInputStream(path)) {
            return calculateCrc(inputStream);
        }
    }

    public static long calculateCrc(File file) throws IOException {
        Objects.requireNonNull(file, "file must not be null");

        return calculateCrc(file.toPath());
    }
}
