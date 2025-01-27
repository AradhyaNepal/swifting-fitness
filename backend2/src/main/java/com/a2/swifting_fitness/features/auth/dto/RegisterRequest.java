package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.ValidationConstant;
import com.a2.swifting_fitness.common.enums.Gender;
import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = StringConstants.firstNameRequired)
    private String firstName;

    @NotEmpty(message = StringConstants.secondNameRequired)
    private String secondName;


    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;

    @Pattern(regexp = ValidationConstant.passwordRegex, message = StringConstants.passwordRegexMessage)
    @Size(min = ValidationConstant.passwordMinLength, message = StringConstants.passwordMinLengthMessage)
    private String password;


}
