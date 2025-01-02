package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.features.auth.dto.ForgetPasswordRequest;
import com.a2.swifting_fitness.features.auth.dto.SetPasswordRequest;
import com.a2.swifting_fitness.features.auth.dto.VerifyOTPRequest;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth/forgot-password")
@RestController
@RequiredArgsConstructor
public class ForgotPasswordController {
    final private AuthService service;

    @PostMapping()
    public void forgotPassword(@RequestBody @Valid ForgetPasswordRequest request) {

    }

    @PostMapping(value = "verify-otp")
    public void verifyOTP(@RequestBody @Valid VerifyOTPRequest request) {

    }

    @PostMapping(value = "set-password")
    public void setPassword(@RequestBody @Valid SetPasswordRequest request) {

    }


}
