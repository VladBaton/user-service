package com.github.vladbaton.userservice.exception;

public class UserNotFoundException extends Exception {
    private Long userId;

    public UserNotFoundException(Long userId) {
        this.userId = userId;
    }

    public UserNotFoundException(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
