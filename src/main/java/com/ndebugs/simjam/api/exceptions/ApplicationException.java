package com.ndebugs.simjam.api.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    
    private final HttpStatus status;

    public ApplicationException(HttpStatus status) {
        this.status = status;
    }

    public ApplicationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
