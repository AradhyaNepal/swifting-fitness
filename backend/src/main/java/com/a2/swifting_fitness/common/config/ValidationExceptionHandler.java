package com.a2.swifting_fitness.common.config;

import com.a2.swifting_fitness.common.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<String> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StringBuilder error = new StringBuilder();
        for (var e : ex.getAllErrors()) {
            error.append("\n").append(e.getDefaultMessage());
        }
        return GenericResponse.error(error.toString(), HttpStatus.BAD_REQUEST);
    }
}
