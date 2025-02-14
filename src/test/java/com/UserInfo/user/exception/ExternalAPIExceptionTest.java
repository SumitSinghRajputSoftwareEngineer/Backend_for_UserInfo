package com.UserInfo.user.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExternalAPIExceptionTest {

    @Test
    void testExternalAPIException_WithMessageOnly() {
        String message = "External API error occurred";

        ExternalAPIException exception = new ExternalAPIException(message);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }

    @Test
    void testExternalAPIException_WithMessageAndCause() {
        String message = "Failed to fetch data from external API";
        Throwable cause = new RuntimeException("Timeout occurred");

        ExternalAPIException exception = new ExternalAPIException(message, cause);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
    }
}