package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AdminNewDeviceVerifyRequest {

    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;

    @NotEmpty(message = StringConstants.otpRequired)
    private String otp;


    @NotEmpty(message = StringConstants.deviceIdRequired)
    private String deviceId;
}
