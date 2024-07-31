package com.hieushin.identity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User already exists"),
    USERNAME_INVALID(1002, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters"),
    USER_NOT_FOUND(1004, "User not found"),
    INVALID_KEY(1005, "Invalid message key"),
    UNAUTHENTICATED(1006, "Unauthenticated user"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
