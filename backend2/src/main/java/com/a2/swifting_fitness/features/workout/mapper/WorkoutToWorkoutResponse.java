package com.a2.swifting_fitness.features.workout.mapper;

import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.workout.dto.WorkoutResponse;
import com.a2.swifting_fitness.features.workout.entity.Workout;

public class WorkoutToWorkoutResponse {

    static  public WorkoutResponse map(Workout workout){
            return WorkoutResponse.builder()
                    .id(workout.getId())
                    .image(workout.getImage().getId())
                    .video(workout.getVideo().getId())
                    .name(workout.getName())
                    .calories(workout.getCalories())
                    .duration(workout.getDurationMin())
                    .build();

    }
}
