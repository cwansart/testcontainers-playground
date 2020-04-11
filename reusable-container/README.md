# Reusable Testcontainers

Reusable Testcontainers is a way to reuse the same container for multiple tests to optimize the test speed.

It has been implemented and documented in Sergei's pull request: https://github.com/testcontainers/testcontainers-java/pull/1781

To use reusable containers a setting in your local `testcontainers.properties` file is required. Check the 
[documentation](https://www.testcontainers.org/features/configuration/) to find the location of your local properties
file.
Open the file in an editor an add the configuration: `testcontainers.reuse.enable=true`

For example for macOS and Linux you could run once:

```
$ echo "testcontainers.reuse.enable=true" >> ~/.testcontainers.properties
```

# Run the tests

To run the tests simply start it with Maven:

```
$ mvn clean verify
```

This will run the integration tests. Check the logs for the messages that are similar to this (except for the container
id, here `06791b077536`):

> The application on host 3f821206c456 has been started.

You should find it twice. If you have `testcontainers.reuse.enable` to `true` the id in both outputs should be equal.

# Benchmark

I've ran the tests with and without `testcontainers.reuse.enable` being enabled. Here are the results:

| #   | Command            | testcontainers.reuse.enable   | Time      |
| --- | ------------------ | ----------------------------- | --------: |
| 1   | `mvn clean verify` | `false`                       | 03:55 min |
| 2   | `mvn clean verify` | `false`                       | 03:44 min |
| 3   | `mvn clean verify` | `false`                       | 03:42 min |
|     |                    |                               |           |
| 1   | `mvn clean verify` | `true`                        | 02:23 min |
| 2   | `mvn clean verify` | `true`                        | 02:25 min |
| 3   | `mvn clean verify` | `true`                        | 02:24 min |
