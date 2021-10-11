package com.example.springboot.dao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class DateTimeExceptionController  {
    @ExceptionHandler(value = DateTimeConflictException.class)
    public ResponseEntity<CustomErrorResponse> handleGenericNotFoundException(DateTimeConflictException e) {
        CustomErrorResponse error = new CustomErrorResponse("CONFLICT_ERROR", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.CONFLICT.value()));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
