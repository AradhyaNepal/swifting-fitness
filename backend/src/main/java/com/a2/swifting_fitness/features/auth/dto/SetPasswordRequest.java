package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.ValidationConstant;
import com.a2.swifting_fitness.common.enums.StringConstants;
import jakarta.validation.constraints.*;

public class SetPasswordRequest {
    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;

    @NotEmpty(message = StringConstants.otpRequired)
    private String otp;

    @Pattern(regexp = ValidationConstant.passwordRegex, message = StringConstants.passwordRegexMessage)
    @Min(value = ValidationConstant.passwordMinLength, message = StringConstants.passwordMinLengthMessage)
    private String password;
}
