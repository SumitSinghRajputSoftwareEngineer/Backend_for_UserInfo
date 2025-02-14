package com.UserInfo.user.exception;

public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) {
        super(message);
    }

    public ExternalAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}