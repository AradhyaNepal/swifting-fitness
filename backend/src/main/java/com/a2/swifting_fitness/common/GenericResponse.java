package com.a2.swifting_fitness.common;


import lombok.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


@Getter

public class GenericResponse<T> extends ResponseEntity<T> {

    private final String message;

    private final T data;


    @Builder
    public GenericResponse(HttpStatusCode statusCode, String message, T data) {
        super(statusCode);
        this.data = data;
        this.message = message;
    }


    public static <T> GenericResponse<T> success(T data, String message) {
        return GenericResponse.<T>builder()
                .message(message)
                .data(data)
                .build();
    }


    public static <T> GenericResponse<T> error(String message, HttpStatusCode statusCode) {
        return GenericResponse.<T>builder()
                .message(message)
                .statusCode(statusCode)
                .build();
    }

}