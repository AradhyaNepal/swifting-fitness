package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.enums.StringConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordRequest {
    @NotEmpty(message = StringConstants.emailRequired)
    @Email(message = StringConstants.emailNotValid)
    private String email;
}
