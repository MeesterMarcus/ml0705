# Tool Rental POS Program - Java

**Author: Marcus Lorenzana**

## Introduction
This program is a Java-based application that allows you to rent tools and generate rental agreements.

## Command Line Instructions
1. Build the application with `./gradlew build`
2. Run the application with `./gradlew run`
3. Run tests with `./gradlew test`

## Intellij Instructions
1. Open the project with Intellij, and allow `gradle` to build the app.
2. Choose the run configuration `tool-rental [run]` and click the run button to run the app.
3. Choose the run configuration `tool-rental [test]` and click the run button to run the unit tests.

## Requirements
- Java 11 or higher

## Unit Tests
- Unit tests are stored in `test/java/tool_rental`

## Additional Notes
- A simple GitHub Action has been set up to run the test suite on each push to ensure no regression issues occur with new pushes.
