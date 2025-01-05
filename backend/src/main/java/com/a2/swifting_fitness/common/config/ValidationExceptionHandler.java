//package com.a2.swifting_fitness.common.config;
//
//import com.a2.swifting_fitness.common.model.GenericResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ValidationExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<GenericResponse<Void>> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
//        StringBuilder error = new StringBuilder();
//        var errors = ex.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
//        return GenericResponse.error(errors, HttpStatus.BAD_REQUEST);
//    }
//}
