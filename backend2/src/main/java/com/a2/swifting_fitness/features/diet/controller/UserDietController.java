package com.a2.swifting_fitness.features.diet.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponse;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.service.DietService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/user/diet")
@AllArgsConstructor
public class UserDietController {
    final DietService service;

    @GetMapping
    GenericResponseEntity<Page<Diet>> getAllDiet(@PathParam(value = "pageSize") Integer pageSize, @PathParam(value = "pageNumber") Integer pageNumber) {
        return GenericResponseEntity.successWithData(service.getDietList(pageSize, pageSize), StringConstants.dietFetchedSuccessfully);
    }
}
