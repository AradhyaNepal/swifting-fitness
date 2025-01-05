package com.a2.swifting_fitness.features.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
public class AuthenticatedResponse {
    private String accessToken;
    private String refreshToken;
}
