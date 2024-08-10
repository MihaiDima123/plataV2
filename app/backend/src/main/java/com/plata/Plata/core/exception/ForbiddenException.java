package com.plata.Plata.core.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends TranslatedException {
    public ForbiddenException() {
        super("");
        this.status = HttpStatus.FORBIDDEN;
    }
}
