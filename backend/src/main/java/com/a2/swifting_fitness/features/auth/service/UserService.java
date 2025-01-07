package com.a2.swifting_fitness.features.auth.service;


import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.UserFromSecurityContext;
import com.a2.swifting_fitness.features.auth.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.TokenRefreshedResponse;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    final FitnessFolksRepository userRepo;
    final RefreshTokenService refreshTokenService;
    final JwtService jwtService;

    public TokenRefreshedResponse refreshToken(String refreshToken) throws CustomException {
        var user = UserFromSecurityContext.get();
        var newRefreshToken = refreshTokenService.verifyThenDeleteOrCreateNew(refreshToken, user).getToken();
        var newAccessToken = jwtService.generateToken(user);
        return TokenRefreshedResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
