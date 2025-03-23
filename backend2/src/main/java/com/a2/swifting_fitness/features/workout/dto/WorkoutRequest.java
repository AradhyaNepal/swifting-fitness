package com.a2.swifting_fitness.features.workout.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
public class WorkoutRequest {
    @NotEmpty(message = StringConstants.workoutNameMustNotBeEmpty)
    private String name;


    @NotNull(message = StringConstants.workoutProteinMustNotBeEmpty)
    @Min(value = 0,message = StringConstants.workoutCaloriesMustBeGreaterThanZero)
    private  Integer calories;


    private  MultipartFile image;
    private  MultipartFile video;

    @NotNull(message = StringConstants.workoutDurationMustNotBeEmpty)
    @Min(value = 0,message = StringConstants.workoutMustBeGreaterThanZero)
    private  Integer durationMin;

}
