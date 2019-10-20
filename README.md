# Martian Robots

## Requirements

You need either Java 12 or docker installed in your computer.

## Usage with local Java

### Using the command line to input the instructions.

```bash
./mvnw clean install exec:java -DskipTests
```

### Using a file containing the instructions.

```bash
./mvnw clean install exec:java -Dexec.args="simulation.txt" -DskipTests
```

### Tests

```bash
./mvnw clean test
```

After the execution you can review the test results opening `spock-results/index.html` with your browser.

## Usage with Docker

First of all build the image
```bash
docker build -t martian-robots .
```

### Using the command line to input the instructions.

```bash
docker run martian-robots mvn clean install exec:java -DskipTests
```

### Using a file containing the instructions (you can use any txt file placed at the root of the project).

```bash
docker run martian-robots mvn clean install exec:java -Dexec.args="simulation.txt" -DskipTests
```

### Tests

```bash
docker run -v <project-folder>/spock-reports:/project/spock-reports martian-robots mvn clean test
```

After the execution you can review the test results opening `spock-results/index.html` with your browser.
