package com.a2.swifting_fitness.common.model;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;


public class GenericResponseEntity<T> extends ResponseEntity<GenericResponse> {
    public GenericResponseEntity(GenericResponse data, HttpStatus status) {
        super(data, status);
    }

    public GenericResponseEntity(T data, String message) {
        super(new GenericSuccessResponse<>(data, message), HttpStatus.OK);
    }


//    public static <T> GenericResponseEntity error(String error, HttpStatus code) {
//        return new GenericResponseEntity(new GenericErrorResponse<>(error, null), code);
//    }
//
//    public static <T> GenericResponseEntity error(String error, HttpStatus code, T flags) {
//        return new GenericResponseEntity(new GenericErrorResponse<>(error, flags), code);
//    }


}




