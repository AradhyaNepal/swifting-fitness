package com.a2.swifting_fitness.common;


import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


@Builder

public class GenericResponse<T> extends ResponseEntity<T> {

    private Boolean success;
    private String message;
    private HttpStatusCode sCode;
    private T data;

    @Override
    public HttpStatusCode getStatusCode() {
        return sCode;
    }

    public static <T> GenericResponse<T> success(T data, String message) {
        return GenericResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .sCode(HttpStatus.OK)
                .build();
    }




    public static <T> GenericResponse<T> error(String message, HttpStatusCode statusCode) {
        return GenericResponse.<T>builder()
                .success(false)
                .message(message)
                .sCode(statusCode)
                .build();
    }

}