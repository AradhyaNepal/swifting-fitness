package com.a2.swifting_fitness.features.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RefreshTokenResponse {
    private  String refreshToken;
    private  String accessToken;
}
