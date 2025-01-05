package com.a2.swifting_fitness.common.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GenericResponseEntity<T> extends ResponseEntity<GenericResponse<T>> {


    private GenericResponseEntity(T data, String message, HttpStatus status, List<String> errors) {
        super(new GenericResponse<>(data, message, errors), status);
    }


    public static <T> GenericResponseEntity<T> successWithData(T data, String message) {
        return new GenericResponseEntity<>(data, message, HttpStatus.OK, null);
    }

    public static GenericResponseEntity<Void> successMessage(String message) {
        return new GenericResponseEntity<>(null, message, HttpStatus.OK, null);
    }


    public static GenericResponseEntity<Void> errors(List<String> errors, HttpStatus status) {
        return new GenericResponseEntity<>(null, "Something went wrong!", status, errors);
    }
    public static GenericResponseEntity<Void> errorsWithFlags(List<String> errors, HttpStatus status) {
        return new GenericResponseEntity<>(null, "Something went wrong!", status, errors);
    }
}
