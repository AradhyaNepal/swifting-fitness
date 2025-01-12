package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.entity.RefreshToken;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import com.a2.swifting_fitness.features.auth.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {


    private final RefreshTokenRepository refreshTokenRepository;


    private final FitnessFolksRepository userRepository;
    private final BlockUserService blockUserService;

    public RefreshToken createRefreshToken(FitnessFolks user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plus(Period.ofDays(30)))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    public RefreshToken verifyThenDeleteOrCreateNew(String token, FitnessFolks user) throws CustomException {
        if (!user.isAccountNonLocked()) {
            throw new CustomException(StringConstants.userLockedCannotContinue);
        }
        var refreshToken = refreshTokenRepository.findByToken(token);
        var now = Instant.now();
        if (refreshToken.isPresent()) {
            var tokenGet = refreshToken.get();
            if (tokenGet.getExpiryDate().isBefore(now)) {
                blockUserService.setBlockUser(user);
                userRepository.save(user);
                refreshTokenRepository.delete(tokenGet);
                throw new CustomException(StringConstants.refreshTokenExpired, HttpStatus.FORBIDDEN);
            }
            tokenGet.setExpiryDate(now); //R1 gets expired
            refreshTokenRepository.save(tokenGet);
            return createRefreshToken(user); //Generate new R2

        } else {
            blockUserService.setBlockUser(user);
            userRepository.save(user);
            throw new CustomException(StringConstants.invalidRefreshToken, HttpStatus.FORBIDDEN);
        }

    }


    @Scheduled(cron = "0 0 0 * * ?")//12 AM
//    @Scheduled(fixedDelay = 10000)
    public void deleteExpiredRefreshToken() {
        System.out.println("Deleting Expired Refreshed Token Scheduler called");
        refreshTokenRepository.deleteAll(refreshTokenRepository.findAll().stream().filter(e -> e.getExpiryDate().plus(7, ChronoUnit.DAYS).isBefore(Instant.now())).toList());
    }


}
