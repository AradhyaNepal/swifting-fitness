package com.a2.swifting_fitness.features.diet.mapper;

import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.entity.Diet;

public class DietToDietResponse {

    static  public DietResponse map(Diet diet){
            return DietResponse.builder()
                    .id(diet.getId())
                    .image(diet.getImage().getId())
                    .name(diet.getName())
                    .calories(diet.getCalories())
                    .protein(diet.getProtein())
                    .fat(diet.getFat())
                    .carbs(diet.getCarbs())
                    .build();

    }
}
