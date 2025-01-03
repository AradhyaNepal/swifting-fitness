package com.a2.swifting_fitness.common.model;

import lombok.*;

import java.util.Map;


@Getter

abstract public class GenericResponse<T> {
    final private T data;

    public GenericResponse(T data) {
        this.data = data;
    }

}

@Getter
class GenericSuccessResponse<T> extends GenericResponse<T> {

    final private String message;

    @Builder
    public GenericSuccessResponse(T data, String message) {
        super(data);
        this.message = message;
    }
}

@Getter
class GenericErrorResponse<T> extends GenericResponse<T> {
    final private String error;
    final private Map<String, Boolean> flags;


    public GenericErrorResponse(String error) {
        super(null);
        this.error = error;
        this.flags = null;
    }
    @Builder
    public GenericErrorResponse(String error, Map<String, Boolean> flags) {
        super(null);
        this.error = error;
        this.flags = flags;
    }
}


