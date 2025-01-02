package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.features.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth/forgot-password")
@RestController
@RequiredArgsConstructor
public class ForgotPasswordController {
    final private AuthService service;

    @PostMapping()
    public void forgotPassword() {

    }

    @PostMapping(value = "verify-otp")
    public void verifyOTP() {

    }

    @PostMapping(value = "set-password")
    public void setPassword() {

    }


}
