package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenRequest;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenResponse;
import com.a2.swifting_fitness.features.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/refresh")
@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    final private UserService userService;

    @PostMapping()
    public GenericResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshToken) throws CustomException {
        return GenericResponseEntity.successWithData(userService.refreshToken(refreshToken), StringConstants.refreshTokenSuccessful);
    }
}
