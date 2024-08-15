package com.plata.Plata.core.messages;

public enum ApiMessages {
    USER_REGISTERED_SUCCESSFULLY("auth.messages.success.registered-successfully");

    private final String message;

    ApiMessages(String message) {
        this.message = message;
    }

    public String value() {
        return message;
    }
}
