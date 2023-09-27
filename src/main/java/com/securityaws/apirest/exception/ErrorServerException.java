package com.securityaws.apirest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorServerException extends RuntimeException {
    public ErrorServerException(String msg) {
        super(msg);
    }
}
