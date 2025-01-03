package com.a2.swifting_fitness.common.model;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class GenericResponseEntity<T> extends ResponseEntity<GenericResponse<T>> {
    public GenericResponseEntity(GenericResponse<T> data, HttpStatus status) {
        super(data, status);
    }

    public static <T> GenericResponseEntity<T> success(T data, String message) {
        return new GenericResponseEntity<T>(new GenericSuccessResponse<>(data, message), HttpStatus.OK);
    }


    public static <T> GenericResponseEntity<T> error(String error, HttpStatus code) {
        return new GenericResponseEntity<>(new GenericErrorResponse<>(error), code);
    }

    public static <T> GenericResponseEntity<T> error(String error, HttpStatus code, Map<String, Boolean> flags) {
        return new GenericResponseEntity<T>(new GenericErrorResponse<>(error, flags), code);
    }


}




