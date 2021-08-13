package com.ndebugs.simjam.api.controllers;

import com.ndebugs.simjam.api.exceptions.ApplicationException;
import com.ndebugs.simjam.api.models.ResponseMessage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindingResult(ex, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return toResponseEntity(status, null, body);
    }
    
    private ResponseEntity<Object> handleBindingResult(BindingResult result, HttpStatus status) {
        Map<String, List<String>> map = new LinkedHashMap();
        
        result.getFieldErrors().forEach(field -> {
            String key = field.getField();
            List<String> values = map.get(key);
            if (values == null) {
                values = new ArrayList();
                map.put(key, values);
            }
            values.add(field.getDefaultMessage());
        });
        
        return toResponseEntity(status, null, map);
    }
    
    private ResponseEntity<Object> toResponseEntity(HttpStatus status, String message, Object data) {
        if (message == null) {
            message = status.getReasonPhrase();
        }
        
        ResponseMessage body = ResponseMessage.error(status.value(), message, data);
        return ResponseEntity.ok(body);
    }
    
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Object> handleApplicationException(ApplicationException e) {
        return toResponseEntity(e.getStatus(), e.getMessage(), null);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, null, null);
    }
}
