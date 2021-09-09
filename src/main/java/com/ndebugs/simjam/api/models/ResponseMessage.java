package com.ndebugs.simjam.api.models;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    private ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" + "code=" + code + ", message=" + message + ", data=" + data + ", timestamp=" + timestamp + '}';
    }
    
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage(HttpStatus.OK.value(), null, data);
    }
    
    public static ResponseMessage error(Integer code, String message) {
        return error(code, message, null);
    }
    
    public static <T> ResponseMessage<T> error(Integer code, String message, T data) {
        return new ResponseMessage(code, message, data);
    }
}
