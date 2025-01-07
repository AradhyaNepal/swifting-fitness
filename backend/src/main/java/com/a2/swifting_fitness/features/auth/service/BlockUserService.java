package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
@Service
public class BlockUserService {
    public void setBlockUser(FitnessFolks user) {
        var wrongAttempts = user.getWrongAttempts() + 1;
        if (wrongAttempts >= 5) {
            user.setWrongAttempts(0);
            user.setIsBlockedTill(Instant.now().plus(12, ChronoUnit.HOURS));
        } else {
            user.setWrongAttempts(wrongAttempts);
        }
    }

    public void removeUserAllBlockageAndSave(FitnessFolks user, FitnessFolksRepository repository) {
        user.setWrongAttempts(0);
        user.setIsBlockedTill(null);
        repository.save(user);
    }
}

