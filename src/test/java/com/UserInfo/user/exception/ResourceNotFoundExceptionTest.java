package com.UserInfo.user.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundException_WithMessage() {
        String message = "User not found";

        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertThat(exception.getMessage()).isEqualTo(message);
        assertThat(exception.getCause()).isNull();
    }
}
