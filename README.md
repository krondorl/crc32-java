<p align="center">
  <img width="180" src="assets/crc32-java-logo.svg" alt="crc32-java logo">
</p>
<br/>

# crc32-java

Crc32 (cyclic redundancy check) in Java.

## Overview

Java implementation of the CRC-32 checksum algorithm. It provides a compact, dependency free API for computing CRC-32 values, with tests and examples included.

## Tech Stack

🔴 Java 25

## Dependencies

📦 No runtime dependencies.

## Features

- 🔬 Small, focused CRC-32 implementation in Java
- 🧪 Unit tests

## Usage

```Java
public static long calculateCrc(byte[] data) {
```

[Check example at Crc32.java](https://github.com/krondorl/crc32-java/blob/main/src/main/java/org/example/Crc32.java#L30-L45)

**Warning**: in any other cases you need to handle conversion to byte array manually.

## License

Please check the [`LICENSE`](LICENSE) file.

## Links

- [Wikipedia: Cyclic redundancy check](https://en.wikipedia.org/wiki/Cyclic_redundancy_check)
- [CRC32 Demystified](https://github.com/Michaelangel007/crc32)

## History

- 13th September, 2026: refactor to use offset, length, stream, file, path
- 12th September, 2026: removed Main.java, added Javadoc, refactored exceptions, Gradle deleted, docs updated, Uint8Array renamed, private constructor, final class
- 11th September, 2026: Java port made based on crc32-ts, README, assets.
