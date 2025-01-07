package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponse;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.AuthenticatedResponse;
import com.a2.swifting_fitness.features.auth.dto.LoginRequest;
import com.a2.swifting_fitness.features.auth.dto.TokenRefreshedResponse;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import com.a2.swifting_fitness.features.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/auth/login")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class LoginController {
    final private AuthService service;
    final private UserService userService;

    @PostMapping()
    public GenericResponseEntity<AuthenticatedResponse> login(@RequestBody @Valid LoginRequest request) throws CustomException {
        return GenericResponseEntity.successWithData(service.login(request),
                StringConstants.loggedInSuccessfully);
    }

    @GetMapping(value = "test")
    public GenericResponseEntity<TokenRefreshedResponse> test() throws CustomException {
        return GenericResponseEntity.successWithData(userService.refreshToken(""),
                StringConstants.loggedInSuccessfully);
    }

}
