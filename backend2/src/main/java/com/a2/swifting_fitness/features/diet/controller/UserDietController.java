package com.a2.swifting_fitness.features.diet.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.service.DietService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/v1/user/diet")
@AllArgsConstructor
@RestController
public class UserDietController {
    final DietService service;

    @GetMapping()
    GenericResponseEntity<List<DietResponse>> getAllDiet(@PathParam(value = "pageSize") Integer pageSize, @PathParam(value = "pageNumber") Integer pageNumber) {
        return GenericResponseEntity.successWithPagination(service.getDietList(pageSize, pageNumber-1), StringConstants.dietFetchedSuccessfully);
    }
}
