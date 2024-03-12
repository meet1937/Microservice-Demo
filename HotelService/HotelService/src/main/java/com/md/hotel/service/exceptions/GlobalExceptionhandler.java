package com.md.hotel.service.exceptions;

import com.md.hotel.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionhandler {
    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourseNotFoundException resourceNotFoundException) {

        String ex = resourceNotFoundException.getMessage();

        return new ResponseEntity<>(ApiResponse.builder().message(ex).status(HttpStatus.NOT_FOUND).success(true).build(), HttpStatus.NOT_FOUND);
    }
}
