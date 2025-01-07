package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
@Service
public class BlockUserService {
    public void blockUser(FitnessFolks user) {
        var wrongAttempts = user.getWrongAttempts() + 1;
        if (wrongAttempts >= 5) {
            user.setWrongAttempts(0);
            user.setIsBlockedTill(Instant.now().plus(2, ChronoUnit.HOURS));
        } else {
            user.setWrongAttempts(wrongAttempts);
        }
    }
}

