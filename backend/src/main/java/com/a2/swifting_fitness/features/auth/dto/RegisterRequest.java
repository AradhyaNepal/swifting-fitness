package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.ValidationConstant;
import com.a2.swifting_fitness.common.enums.Gender;
import com.a2.swifting_fitness.common.StringConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = StringConstants.ageRequired)
    @Size(max = ValidationConstant.ageMaxLength, min = ValidationConstant.ageMinLength, message = StringConstants.ageNotValid)
    private int age;

    @NotEmpty(message = StringConstants.genderRequired)
    private Gender gender;


}
