package com.a2.swifting_fitness.common.model;

import lombok.*;


@Data
abstract public class GenericResponse {
}


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
class GenericSuccessResponse<T> extends GenericResponse {

    private final String message;
    private final T data;

    public GenericSuccessResponse(T data, String message) {
        this.message = message;
        this.data = data;
    }


}

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
class GenericErrorResponse<T> extends GenericResponse {
    final private String error;
    final private T errorFlags;

}


