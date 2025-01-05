package com.a2.swifting_fitness.features.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
public class AuthenticatedResponse {
    private String accessToken;
    private String refreshToken;
}
