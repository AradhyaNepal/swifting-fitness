package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.SendOTPToEmailRequest;
import com.a2.swifting_fitness.features.auth.dto.SetPasswordRequest;
import com.a2.swifting_fitness.features.auth.dto.VerifyOTPAndSetPasswordRequest;
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


    @PostMapping(value = "send-otp-to-email")
    public GenericResponseEntity<Void> resendOTP(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.sendOTPToEmail(request, OTPPurpose.forgotPassword);
        return GenericResponseEntity.successWithMessage(StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "verify-and-set-password")
    public GenericResponseEntity<Void> verifyAndSetPassword(@RequestBody @Valid VerifyOTPAndSetPasswordRequest request) throws CustomException {
        service.forgotPasswordVerifyAndSetPassword(request);
        return GenericResponseEntity.successWithMessage(StringConstants.passwordChangedSuccessfullyPleaseLogin);
    }


}

