/*!
 * crc32-java
 *
 * Copyright (c) 2026- Adam Burucs
 *
 * MIT Licensed
 */

package org.example;

import java.nio.charset.StandardCharsets;

public class Main {
    static void printIntro() {
        System.out.println();
        System.out.println("Crc32 library test");
        System.out.println();
    }

    static void printCrc(String text) {
        byte[] uInt8Array = (text != null) ? text.getBytes(StandardCharsets.UTF_8) : null;
        System.out.println("String " + text);

        try {
            long crcResult = Crc32.calculateCrc(uInt8Array);
            System.out.println(String.format("✅ crc32 value 0x%x", crcResult));
        } catch (Exception error) {
            System.err.println("🔴 An error occurred: " + error.getMessage());
        }

        System.out.println();
    }

    public static void main(String[] args) {
        printIntro();
        printCrc("1234567");
        printCrc("");
    }
}