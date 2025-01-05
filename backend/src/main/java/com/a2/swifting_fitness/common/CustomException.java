package com.a2.swifting_fitness.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends  Exception{
    private final HttpStatus status;

    public CustomException(String message) {
        super(message);
        status=HttpStatus.CONFLICT;
    }

    public CustomException(String message,HttpStatus status) {
        super(message);
        this.status=status;
    }
}
