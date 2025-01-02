package com.a2.swifting_fitness.features.auth.model;

import com.a2.swifting_fitness.common.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JWTExtracted {
    private  String uid;
    private UserRole role;
}
