package com.a2.swifting_fitness.features.workout.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.workout.dto.WorkoutRequest;
import com.a2.swifting_fitness.features.workout.dto.WorkoutResponse;
import com.a2.swifting_fitness.features.workout.service.WorkoutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(value = "/api/v1/admin/workout")

@RequiredArgsConstructor
@RestController
public class AdminWorkoutController {
    final WorkoutService service;

    @SecurityRequirement(name = "Authorization")
    @SecurityRequirement(name = "Device-Id")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseEntity<WorkoutResponse> saveWorkout(@Valid @ModelAttribute WorkoutRequest request) throws IOException, CustomException {
        return  GenericResponseEntity.successWithData(service.addWorkout(request),StringConstants.dietAddedSuccessfully);
    }
}
