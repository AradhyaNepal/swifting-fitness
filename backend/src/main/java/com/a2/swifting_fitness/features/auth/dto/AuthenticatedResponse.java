package com.a2.swifting_fitness.features.auth.dto;

import lombok.Builder;

@Builder
public class AuthenticatedResponse {
    private String accessToken;
    private String refreshToken;
}
