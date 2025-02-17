package com.a2.swifting_fitness.features.diet.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.service.DietService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/api/v1/admin/diet")

@RequiredArgsConstructor
@RestController
public class AdminDietController {
    final DietService service;

    @SecurityRequirement(name = "Authorization")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseEntity<DietResponse> saveDiet(@Valid @ModelAttribute DietRequest request) throws IOException, CustomException {
        return  GenericResponseEntity.successWithData(service.addDiet(request),StringConstants.dietAddedSuccessfully);
    }
}
