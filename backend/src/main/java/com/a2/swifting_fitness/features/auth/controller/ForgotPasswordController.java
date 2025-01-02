package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.GenericResponse;
import com.a2.swifting_fitness.common.enums.StringConstants;
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
    public GenericResponse<Void> forgotPassword(@RequestBody @Valid ForgetPasswordRequest request) {
        return GenericResponse.success(null, StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "verify-otp")
    public GenericResponse<Void> verifyOTP(@RequestBody @Valid VerifyOTPRequest request) {
        return GenericResponse.success(null, StringConstants.otpVerifiedSuccessfully);
    }

    @PostMapping(value = "set-password")
    public GenericResponse<Void> setPassword(@RequestBody @Valid SetPasswordRequest request) {
        return GenericResponse.success(null, StringConstants.passwordChangedSuccessfullyPleaseLogin);
    }


}
