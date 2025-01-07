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
    @NotEmpty(message = StringConstants.fullNameRequired)
    private String fullName;


    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;

    @NotNull(message = StringConstants.ageRequired)
    @Max(value = ValidationConstant.ageMaxLength, message = StringConstants.ageNotValid)
    @Min(value = ValidationConstant.ageMinLength, message = StringConstants.ageNotValid)
    private Integer age;

    @NotNull(message = StringConstants.genderRequired)
    private Gender gender;


}
