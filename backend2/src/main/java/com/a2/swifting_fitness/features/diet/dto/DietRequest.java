package com.a2.swifting_fitness.features.diet.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
public class DietRequest {
    @NotEmpty(message = StringConstants.dietNameMustNotBeEmpty)
    private String name;

    @NotNull(message = StringConstants.dietCaloriesMustNotBeEmpty)
    @Min(value = 0,message = StringConstants.proteinMustBeGreaterThanZero)
    private  Integer protein;

    @NotNull(message = StringConstants.dietProteinMustNotBeEmpty)
    @Min(value = 0,message = StringConstants.caloriesMustBeGreaterThanZero)
    private  Integer calories;


    @Min(value = 0,message = StringConstants.fatMustBeGreaterThanZero)
    private  Integer fat;
    @Min(value = 0,message = StringConstants.carbsMustBeGreaterThanZero)
    private  Integer carbs;

    private  MultipartFile image;

}
