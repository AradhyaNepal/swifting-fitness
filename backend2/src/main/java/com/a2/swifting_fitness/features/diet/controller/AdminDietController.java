package com.a2.swifting_fitness.features.diet.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.service.DietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping(value = "/api/v1/admin/diet")
@RestController
@RequiredArgsConstructor
public class AdminDietController {
    final DietService service;
    public GenericResponseEntity<Diet> saveDiet(@Valid @RequestPart DietRequest request, @RequestPart MultipartFile image) throws IOException, CustomException {
        return  GenericResponseEntity.successWithData(service.addDiet(request,image),StringConstants.dietAddedSuccessfully);
    }
}
