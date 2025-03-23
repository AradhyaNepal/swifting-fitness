package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.enums.UserTier;
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
    private  String firstName;
    private  String secondName;
    private String profile;
    private int healthyPercentage;
    private UserTier userTire;

    static public UserDetailsResponse fromFitnessFolks(Users users) {
        var image=users.getProfile();
        String profileId=null;
        if(image!=null){
            profileId=image.getId();
        }
        return UserDetailsResponse.builder()
                .uid(users.getUid())
                .firstName(users.getFirstName())
                .secondName(users.getLastName())
                .email(users.getEmail())
                .profile(profileId)
                .healthyPercentage(users.getHealthyPercentage())
                .userTire(users.getUserTier())
                .build();
    }

}
