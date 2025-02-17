package com.a2.swifting_fitness.features.diet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class DietResponse {

    private Integer id;


    private String image;


    private String name;


    private int calories;

    private int protein;

    private Integer fat;

    private Integer carbs;


}
