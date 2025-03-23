package com.a2.swifting_fitness.features.workout.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.service.DietService;
import com.a2.swifting_fitness.features.workout.dto.WorkoutResponse;
import com.a2.swifting_fitness.features.workout.service.WorkoutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/v1/user/workout")
@AllArgsConstructor
@RestController
@SecurityRequirement(name = "Authorization")
public class UserWorkoutController {
    final WorkoutService service;

    @GetMapping()
    GenericResponseEntity<List<WorkoutResponse>> getAllWorkout(@PathParam(value = "pageSize") Integer pageSize, @PathParam(value = "pageNumber") Integer pageNumber) {
        return GenericResponseEntity.successWithPagination(service.getWorkoutList(pageSize, pageNumber-1), StringConstants.workoutFetchedSuccessfully);
    }
}
