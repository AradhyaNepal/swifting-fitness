package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.AuthenticatedResponse;
import com.a2.swifting_fitness.features.auth.dto.LoginRequest;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import com.a2.swifting_fitness.features.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/auth/login")
@RestController
@RequiredArgsConstructor
public class LoginController {
    final private AuthService service;
    final private UserService userService;

    @PostMapping()
    public GenericResponseEntity<AuthenticatedResponse> login(@RequestBody @Valid LoginRequest request) throws CustomException {
        return GenericResponseEntity.successWithData(service.login(request),
                StringConstants.loggedInSuccessfully);
    }


}
