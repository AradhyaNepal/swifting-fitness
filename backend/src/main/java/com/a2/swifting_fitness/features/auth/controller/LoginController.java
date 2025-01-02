package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.features.auth.dto.LoginRequest;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth/login")
@RestController
@RequiredArgsConstructor
public class LoginController {
    final private AuthService service;

    @PostMapping()
    public void login(@RequestBody @Valid LoginRequest request) {

    }

}