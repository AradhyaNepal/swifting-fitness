package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.entity.RefreshToken;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import com.a2.swifting_fitness.features.auth.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Period;
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
        var refreshToken = refreshTokenRepository.findByToken(token);
        var now = Instant.now();
        if (refreshToken.isPresent()) {
            var tokenGet = refreshToken.get();
            if (tokenGet.getExpiryDate().isBefore(now)) {
                refreshTokenRepository.delete(tokenGet);
                throw new CustomException("Refresh token is expired. Please make a new login..!", HttpStatus.FORBIDDEN);
            }
            tokenGet.setExpiryDate(now);
            refreshTokenRepository.save(tokenGet);
            return createRefreshToken(user);

        } else {
            blockUserService.blockUser(user);
            userRepository.save(user);
            throw new CustomException("Invalid refresh token", HttpStatus.FORBIDDEN);
        }

    }


}
