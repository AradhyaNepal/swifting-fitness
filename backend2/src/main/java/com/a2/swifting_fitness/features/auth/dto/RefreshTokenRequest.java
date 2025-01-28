package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotEmpty(message = StringConstants.refreshTokenRequired)
    private String refreshToken;

    @NotEmpty(message = StringConstants.refreshTokenRequired)
    private String accessToken;
}
