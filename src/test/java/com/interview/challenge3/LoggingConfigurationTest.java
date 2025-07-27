package com.interview.challenge3;

import com.interview.service.LoggingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Challenge 4: Logging Configuration Test
 * 
 * Add the calling class name to the log output
 */
@SpringBootTest
@ActiveProfiles("unit")
class LoggingConfigurationTest {

    @Autowired
    private LoggingService loggingService;

    @Test
    void testApplicationLogging() {
        loggingService.performOperation("test-operation");
        loggingService.performOperation("another-operation");
        loggingService.performOperation("error");
        loggingService.logAtDifferentLevels();
    }

    @Test
    void testExceptionHandling() {
        loggingService.performOperation("error");
    }
}
