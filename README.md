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

## Build with Gradle

Gradle 9.1 or newer and Java 25 are required. From the repository root:

```bash
gradle build
gradle test
```

To publish the library to your local Maven repository (the Gradle equivalent of
`mvn install`), run:

```bash
gradle publishToMavenLocal
```

The generated JAR is in `build/libs/`.

## Installing with a Git Dependency

This project is not published to a remote Maven repository. To use the version
from GitHub in another Java project, clone it and install the artifact into your
local Maven repository (Java 25 is required):

```bash
git clone https://github.com/krondorl/crc32-java.git
cd crc32-java
mvn install
```

This requires Maven to be installed and available on your `PATH`.

Alternatively, publish it with Gradle:

```bash
git clone https://github.com/krondorl/crc32-java.git
cd crc32-java
gradle publishToMavenLocal
```

This requires Gradle 9.1 or newer and Java 25 to be installed and available on
your `PATH`.

Then add the dependency to the consuming project's `pom.xml`:

```xml
<dependency>
    <groupId>org.example</groupId>
    <artifactId>crc32-java</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

The dependency is resolved from the local Maven repository populated by the
install or publish command. Re-run that command after pulling changes from
GitHub.

For a consuming Gradle project, add `mavenLocal()` to its repositories and the
same dependency coordinates:

```groovy
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation 'org.example:crc32-java:1.0-SNAPSHOT'
}
```

## Usage

The library usage depends on whether you have a String or a byte array.

The `printCrc` function (in `Main.java`) automatically converts the String into a byte array.

```Java
static void printCrc(String text) {
```

[Check example at Main.java](https://github.com/krondorl/crc32-java/blob/main/src/main/java/org/example/Main.java#L20-L32)

If you have a byte array, you can have it directly as an input parameter.

```Java
public static long calculateCrc(byte[] data) {
```

[Check example at Crc32.java](https://github.com/krondorl/crc32-java/blob/main/src/main/java/org/example/Crc32.java#L30-L45)

**Warning**: in any other cases you need to handle conversion to byte array manually.

## Running Main

```bash
mvn package
mvn compile
java -cp target/classes org.example.Main
```

The output will look like this:

```bash
PS C:\Dev\crc32-java> java -cp target/classes org.example.Main

Crc32 library test

String 1234567
? crc32 value 0x5003699f

String
? An error occurred: Cannot calculate CRC for an empty array (possible empty String given).
```

## License

Please check the [`LICENSE`](LICENSE) file.

## Links

- [Wikipedia: Cyclic redundancy check](https://en.wikipedia.org/wiki/Cyclic_redundancy_check)
- [CRC32 Demystified](https://github.com/Michaelangel007/crc32)

## History

- 11th September, 2026: Java port made based on crc32-ts, README, assets.
