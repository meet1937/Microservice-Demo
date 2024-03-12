package com.md.user.service.exceptions;

import com.md.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {

        String ex = resourceNotFoundException.getMessage();

        return new ResponseEntity<>(ApiResponse.builder().message(ex).status(HttpStatus.NOT_FOUND).success(true).build(), HttpStatus.NOT_FOUND);
    }
}
