package com.example.springboot.dao.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DateTimeConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;
//    public DateTimeConflictException(String message){
//        super(message);
//    }
//    public  DateTimeConflictException(String message, Throwable cause) {
//        super(message,cause);
//    }
}
