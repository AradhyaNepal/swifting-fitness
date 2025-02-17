package com.a2.swifting_fitness.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.util.List;

@Getter
@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Exclude null fields during serialization
public class GenericResponse<T> {
    private T data;
    private List<String> errors;
    private String message;

    private  int totalItems;
    private  int totalPages;
    private  boolean haveNext;


}
