package com.a2.swifting_fitness.features.workout.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class WorkoutResponse {

    private Integer id;


    private String image;
    private String video;


    private String name;


    private int calories;

    private int duration;




}
