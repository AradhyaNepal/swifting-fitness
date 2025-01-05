package com.a2.swifting_fitness.common.config;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.servlet.http.HttpServletRequest;

//@ControllerAdvice
public class ValidationExceptionHandler {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public GenericResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
//        StringBuilder error = new StringBuilder();
//        for (var e : ex.getAllErrors()) {
//            error.append("\n").append(e.getDefaultMessage());
//        }
//        return GenericResponseEntity.error(error.toString(), HttpStatus.BAD_REQUEST);
//    }
}
