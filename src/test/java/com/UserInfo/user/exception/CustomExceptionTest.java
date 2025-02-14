package com.UserInfo.user.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class CustomExceptionTest {

    @Test
    void testCustomException_WithMessageAndStatus() {
        String message = "Custom error occurred";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomException exception = new CustomException(message, status);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getStatus()).isEqualTo(status);
    }

    @Test
    void testCustomException_WithMessageAndCause() {
        String message = "Unexpected error";
        Throwable cause = new RuntimeException("Root cause");

        CustomException exception = new CustomException(message, cause);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isEqualTo(cause);
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR); // Default status
    }
}