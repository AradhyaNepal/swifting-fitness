package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.features.auth.entity.Users;
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

    static public UserDetailsResponse fromFitnessFolks(Users users) {
        return UserDetailsResponse.builder()
                .uid(users.getUid())
                .fullName(users.getFullName())
                .email(users.getEmail())
                .profile(users.getProfile())
                .healthyPercentage(88)
                .userTire("Pro")
                .build();
    }

}
