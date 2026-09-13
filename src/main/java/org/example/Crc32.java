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

/**
 * Utility class for calculating CRC-32 checksums.
 *
 * <p>This implementation uses the standard CRC-32 polynomial
 * {@code 0xEDB88320} and supports calculating checksums from byte arrays,
 * input streams, paths, and files.</p>
 *
 * <p>The resulting checksum is returned as an unsigned 32-bit value
 * represented by a Java {@code long}.</p>
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
     * Calculates the CRC-32 checksum of the specified byte array.
     *
     * @param data the byte array to process
     * @return the CRC-32 checksum as an unsigned 32-bit value stored in a
     * {@code long}
     * @throws NullPointerException if {@code data} is {@code null}
     */
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

    /**
     * Calculates the CRC-32 checksum of all bytes read from the specified
     * input stream.
     *
     * <p>This method does not close the supplied input stream. The caller is
     * responsible for closing it when necessary.</p>
     *
     * @param inputStream the input stream to read
     * @return the CRC-32 checksum as an unsigned 32-bit value stored in a
     * {@code long}
     * @throws NullPointerException if {@code inputStream} is {@code null}
     * @throws IOException if an I/O error occurs while reading the stream
     */
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

    /**
     * Calculates the CRC-32 checksum of the file referenced by the specified
     * path.
     *
     * <p>The file is opened as an input stream and automatically closed after
     * the checksum has been calculated.</p>
     *
     * @param path the path of the file to process
     * @return the CRC-32 checksum as an unsigned 32-bit value stored in a
     * {@code long}
     * @throws NullPointerException if {@code path} is {@code null}
     * @throws IOException if the file cannot be opened or read
     */
    public static long calculateCrc(Path path) throws IOException {
        Objects.requireNonNull(path, "path must not be null");

        try (InputStream inputStream = Files.newInputStream(path)) {
            return calculateCrc(inputStream);
        }
    }

    /**
     * Calculates the CRC-32 checksum of the specified file.
     *
     * <p>This method delegates to {@link #calculateCrc(Path)} using the file's
     * {@link File#toPath()} representation.</p>
     *
     * @param file the file to process
     * @return the CRC-32 checksum as an unsigned 32-bit value stored in a
     * {@code long}
     * @throws NullPointerException if {@code file} is {@code null}
     * @throws IOException if the file cannot be opened or read
     */
    public static long calculateCrc(File file) throws IOException {
        Objects.requireNonNull(file, "file must not be null");

        return calculateCrc(file.toPath());
    }
}
