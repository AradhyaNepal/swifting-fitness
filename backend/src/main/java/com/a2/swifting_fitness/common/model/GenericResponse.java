package com.a2.swifting_fitness.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;



@JsonInclude(JsonInclude.Include.NON_NULL)
public record GenericResponse<T>(T data, String message,List<String> errors) {
}
