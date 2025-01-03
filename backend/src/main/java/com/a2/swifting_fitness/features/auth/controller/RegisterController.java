package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.features.auth.dto.AuthenticatedResponse;
import com.a2.swifting_fitness.features.auth.dto.RegisterRequest;
import com.a2.swifting_fitness.features.auth.dto.SetPasswordRequest;
import com.a2.swifting_fitness.features.auth.dto.VerifyOTPRequest;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth/register")
@RestController
@RequiredArgsConstructor
public class RegisterController {
    final private AuthService service;


    @PostMapping()
    public GenericResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) {
        return GenericResponseEntity.success(null, StringConstants.registerSuccessfully);
    }

    @PostMapping(value = "/verify-otp")
    public GenericResponseEntity<Void> verifyOTP(@RequestBody @Valid VerifyOTPRequest request) {
        return GenericResponseEntity.success(null, StringConstants.otpVerifiedSuccessfully);
    }

    @PostMapping(value = "/set-password")
    public GenericResponseEntity<AuthenticatedResponse> setPassword(@RequestBody @Valid SetPasswordRequest request) {
        return GenericResponseEntity.success(AuthenticatedResponse.builder().accessToken("AccessToken").refreshToken("RefreshToken").build(), StringConstants.passwordSetSuccessfully);
    }

}
