# Java Debugging Challenge

This is a Java debugging exercise with 4 challenges that need to be fixed.

## Prerequisites

- Java 17+
- Maven 3.6+
- IDE of your choice

## Rules

* You are NOT allowed to use AI-assistance (directly or via search engines). We recommend using DuckDuckGo.com for searching or add `-AI` (minus A I) to the end of your google queries).
* You are allowed and encouraged to read documentation on various languages, libraries, and frameworks in order to solve the challenges.
* The interviewer will answer any questions you have or provide hints on request but if you are stuck, you should skip the challenge and try another.

## Getting Started

1. Import this project into your IDE as a Maven project
2. Run `mvn clean compile` to build the project
3. Start with Challenge 1 and work through them in order

## Challenges

### Challenge 1: Order Processing Test
**Test**: `src/test/java/com/interview/challenge1/OrderProcessingServiceTest.java`

**Issue**: When the tests are ran individually they pass, but fail when ran together (in the same process). Order 
numbering should start from 1 for each new web request.

### Challenge 2: Product Serialization Test
**Test**: `src/test/java/com/interview/challenge2/ProductSerializationTest.java`

**Issue**: Test is failing - JSON serialization and deserialization is not working as expected.

### Challenge 3: Logging Configuration Test
**Test**: `src/test/java/com/interview/challenge3/LoggingConfigurationTest.java`

**Issue**: Add the calling class name to the log output, verify by looking at the output in the `logs/` directory.

### Challenge 4: Database Configuration
**Test**: `src/test/java/com/interview/challenge4/InMemoryDatabaseTest.java`

**Issue**: The application is configured to use MySQL, but tests fail due to missing database connection. Configure the application to use an in-memory H2 database for testing.

### Challenge 5: Transaction Management
**Test**: `src/test/java/com/interview/challenge5/TransactionManagementTest.java`

**Issue**: Audit logs should be saved, even when exceptions are thrown.
