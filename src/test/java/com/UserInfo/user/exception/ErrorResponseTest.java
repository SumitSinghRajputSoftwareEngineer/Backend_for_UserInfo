package com.UserInfo.user.exception;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void testErrorResponse_ConstructorAndGetters() {
        int status = 404;
        String message = "Resource not found";
        LocalDateTime timestamp = LocalDateTime.now();

        ErrorResponse errorResponse = new ErrorResponse(status, message, timestamp);

        assertThat(errorResponse.getStatus()).isEqualTo(status);
        assertThat(errorResponse.getMessage()).isEqualTo(message);
        assertThat(errorResponse.getTimestamp()).isEqualTo(timestamp);
    }

    @Test
    void testErrorResponse_Setters() {
        ErrorResponse errorResponse = new ErrorResponse(500, "Initial Error", LocalDateTime.now());

        int newStatus = 400;
        String newMessage = "Bad Request";
        LocalDateTime newTimestamp = LocalDateTime.now();

        errorResponse.setStatus(newStatus);
        errorResponse.setMessage(newMessage);
        errorResponse.setTimestamp(newTimestamp);

        assertThat(errorResponse.getStatus()).isEqualTo(newStatus);
        assertThat(errorResponse.getMessage()).isEqualTo(newMessage);
        assertThat(errorResponse.getTimestamp()).isEqualTo(newTimestamp);
    }
}