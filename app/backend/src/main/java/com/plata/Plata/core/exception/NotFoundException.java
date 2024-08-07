package com.plata.Plata.core.exception;

import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

public class NotFoundException extends TranslatedException {
    public NotFoundException(@Nonnull String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }
}
