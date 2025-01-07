package com.a2.swifting_fitness.common;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserFromSecurityContext {
    public static FitnessFolks get() throws CustomException {
        var value = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(value instanceof FitnessFolks)) {
            throw new CustomException("Invalid token");
        }
    }
}
