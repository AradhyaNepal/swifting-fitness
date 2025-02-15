package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.AdminLoginRequest;
import com.a2.swifting_fitness.features.auth.dto.AdminNewDeviceVerifyRequest;
import com.a2.swifting_fitness.features.auth.dto.AuthenticatedResponse;
import com.a2.swifting_fitness.features.auth.dto.SendOTPToEmailRequest;
import com.a2.swifting_fitness.features.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/auth/admin")
@RestController
@RequiredArgsConstructor
public class AdminAuthController {
    final private AuthService service;

    @PostMapping(value = "login")
    public GenericResponseEntity<AuthenticatedResponse> login(@RequestBody @Valid AdminLoginRequest request) throws CustomException {
        return GenericResponseEntity.successWithData(service.login(request),
                StringConstants.loggedInSuccessfully);
    }

    @PostMapping(value = "request-new-device")
    public GenericResponseEntity<Void> requestToSetDeviceId(@RequestBody @Valid SendOTPToEmailRequest request) throws CustomException {
        service.sendOTPToEmail(request, OTPPurpose.ADMIN_DEVICE_ID);
        return GenericResponseEntity.successWithMessage(StringConstants.emailSentSuccessfully);
    }

    @PostMapping(value = "verify-new-device-otp")
    public GenericResponseEntity<Void> verifyNewDeviceOTP(@RequestBody @Valid AdminNewDeviceVerifyRequest request) throws CustomException {
        service.adminVerifySetDeviceID(request);
        return GenericResponseEntity.successWithMessage(StringConstants.newDeviceSet);
    }


}
