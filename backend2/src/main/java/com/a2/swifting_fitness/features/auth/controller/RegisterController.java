package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
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
        return  GenericResponseEntity.successWithMessage(service.register(request));
    }

    @PostMapping(value = "/verify-otp")
    public GenericResponseEntity<AuthenticatedResponse> verifyOTP(@RequestBody @Valid VerifyOTPRequest request) throws CustomException {
        service.verifyOTP(request, OTPPurpose.register);
        return GenericResponseEntity.successWithMessage(StringConstants.otpVerifiedSuccessfully);
    }

    @PostMapping(value = "/resend-otp")
    public GenericResponseEntity<Void> resendOTP(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.sendOTPToEmail(request,OTPPurpose.register);
        return GenericResponseEntity.successWithMessage(StringConstants.emailSentSuccessfully);
    }


}
