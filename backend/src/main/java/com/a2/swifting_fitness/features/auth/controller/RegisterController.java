package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.features.auth.dto.*;
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
    public GenericResponseEntity<Void> register(@RequestBody @Valid RegisterRequest request) throws CustomException {
        service.register(request);
        return GenericResponseEntity.success(null, StringConstants.registerSuccessfully);
    }

    @PostMapping(value = "/verify-otp")
    public GenericResponseEntity<Void> verifyOTP(@RequestBody @Valid VerifyOTPRequest request) throws CustomException {
        service.verifyOTP(request);
        return GenericResponseEntity.success(null, StringConstants.otpVerifiedSuccessfully);
    }

    @PostMapping(value = "/resend-otp")
    public GenericResponseEntity<Void> resendOTP(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.resendOTP(request);
        return GenericResponseEntity.success(null, StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "/set-password")
    public GenericResponseEntity<AuthenticatedResponse> setPassword(@RequestBody @Valid SetPasswordRequest request) throws CustomException {
        return GenericResponseEntity.success(service.setRegisterPassword(request), StringConstants.passwordSetSuccessfully);
    }

}
