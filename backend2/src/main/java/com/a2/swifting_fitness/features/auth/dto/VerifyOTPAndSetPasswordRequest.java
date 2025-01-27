package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.constants.ValidationConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyOTPAndSetPasswordRequest {
    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;

    @NotEmpty(message = StringConstants.otpRequired)
    private String otp;

    @Pattern(regexp = ValidationConstant.passwordRegex, message = StringConstants.passwordRegexMessage)
    @Size(min = ValidationConstant.passwordMinLength, message = StringConstants.passwordMinLengthMessage)
    private String password;
}
