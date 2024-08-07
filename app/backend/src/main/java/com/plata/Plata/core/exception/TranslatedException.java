package com.plata.Plata.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TranslatedException extends Exception {
    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public TranslatedException(Throwable cause) {
        super(cause);
    }

    public TranslatedException(String message) {
        super(message);
    }

    public TranslatedException(String message,Throwable cause) {
        super(message, cause);
    }
}
