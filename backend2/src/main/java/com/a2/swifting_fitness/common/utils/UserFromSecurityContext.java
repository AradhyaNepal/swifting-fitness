package com.a2.swifting_fitness.common.utils;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.auth.entity.Users;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserFromSecurityContext {
    public static Users get() throws CustomException {
        var value = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(value instanceof Users)) {
            throw new CustomException(StringConstants.invalidToken);
        }
        return (Users) value;
    }
}
