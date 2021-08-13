package com.ndebugs.simjam.api.exceptions;

import org.springframework.http.HttpStatus;

public class PathNotFoundException extends ApplicationException {
    
    public PathNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
    
    public PathNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
