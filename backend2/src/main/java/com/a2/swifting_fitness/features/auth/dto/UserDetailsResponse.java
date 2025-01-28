package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDetailsResponse {
    private String uid;
    private String email;
    private  String fullName;
    private String profile;
    private int healthyPercentage;
    private String userTire;

    static public UserDetailsResponse fromFitnessFolks(FitnessFolks fitnessFolks) {
        return UserDetailsResponse.builder()
                .uid(fitnessFolks.getUid())
                .fullName(fitnessFolks.getFullName())
                .email(fitnessFolks.getEmail())
                .profile(fitnessFolks.getProfile())
                .healthyPercentage(88)
                .userTire("Pro")
                .build();
    }

}
