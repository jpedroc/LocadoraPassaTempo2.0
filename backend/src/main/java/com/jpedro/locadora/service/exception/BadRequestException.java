package com.jpedro.locadora.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(final String message) {
        this(message, null);
    }

    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
