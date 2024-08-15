package com.plata.Plata.core.messages;

public enum Errors {
    JWT_DECODE_ISSUE("exception.jwt.decode"),
    USER_ALREADY_EXISTS("exception.user.already-exists"),
    USER_NOT_FOUND("exception.user.not-found");

    private final String message;

    Errors(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }
}
