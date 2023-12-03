package com.test.jwt.controller;

import com.test.jwt.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
        BaseResponse<Void> response = new BaseResponse<>(null);
        response.setError("Internal server error: " + e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseResponse<Void>> handleUserNameNotFound(UsernameNotFoundException e) {
        BaseResponse<Void> response = new BaseResponse<>(null);
        response.setError("User Not Found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}