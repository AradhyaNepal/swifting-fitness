package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.SendOTPToEmailRequest;
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
    public GenericResponseEntity<Void> forgotPassword(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.sendOTPToEmail(request, OTPPurpose.forgotPassword);
        return GenericResponseEntity.successWithMessage(StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "resend-otp")
    public GenericResponseEntity<Void> resendOTP(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.sendOTPToEmail(request, OTPPurpose.forgotPassword);
        return GenericResponseEntity.successWithMessage(StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "verify-otp")
    public GenericResponseEntity<Void> verifyOTP(@RequestBody @Valid VerifyOTPRequest request) throws CustomException {
        service.verifyOTP(request, OTPPurpose.forgotPassword);
        return GenericResponseEntity.successWithMessage(StringConstants.otpVerifiedSuccessfully);
    }

    @PostMapping(value = "set-password")
    public GenericResponseEntity<Void> setPassword(@RequestBody @Valid SetPasswordRequest request) throws CustomException {
        service.setPassword(request, OTPPurpose.forgotPassword);
        return GenericResponseEntity.successWithMessage(StringConstants.passwordChangedSuccessfullyPleaseLogin);
    }


}

