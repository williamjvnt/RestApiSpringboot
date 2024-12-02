package com.example.RestApi.Controller;

import com.example.RestApi.dto.Response.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ExceptionRegisterHandling {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RegisterResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(RegisterResponse.<String>builder().message(exception.getStatusCode().toString()).build());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RegisterResponse<String>> apiException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                    .body(RegisterResponse.<String>builder().message(exception.getFieldError().getDefaultMessage()).build());
    }


}
