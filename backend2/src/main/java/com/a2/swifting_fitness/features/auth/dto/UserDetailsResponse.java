package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.enums.Gender;
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
    private int age;
    private Gender gender;
    private String profile;

    static  public UserDetailsResponse fromFitnessFolks(FitnessFolks fitnessFolks){
        return  UserDetailsResponse.builder()
                .uid(fitnessFolks.getUid())
                .email(fitnessFolks.getEmail())
                .age(fitnessFolks.getAge())
                .gender(fitnessFolks.getGender())
                .profile(fitnessFolks.getProfile())
                .build();
    }

}
