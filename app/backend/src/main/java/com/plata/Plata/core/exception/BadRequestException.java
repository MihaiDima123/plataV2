package com.plata.Plata.core.exception;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

public class BadRequestException extends TranslatedException {
    public BadRequestException(@Nonnull String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
