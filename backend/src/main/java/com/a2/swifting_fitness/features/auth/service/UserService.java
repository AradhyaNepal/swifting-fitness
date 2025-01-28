package com.a2.swifting_fitness.features.auth.service;


import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.common.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenRequest;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenResponse;
import com.a2.swifting_fitness.features.auth.dto.UserDetailsResponse;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import com.a2.swifting_fitness.features.auth.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    final FitnessFolksRepository userRepo;
    final RefreshTokenRepository refreshRepo;
    final RefreshTokenService refreshTokenService;
    final JwtService jwtService;

    public RefreshTokenResponse refreshToken( RefreshTokenRequest refreshToken) throws CustomException {


       ne

        var newRefreshToken = refreshTokenService.verifyThenDeleteOrCreateNew(refreshToken.getRefreshToken(), userGet).getToken();
        var newAccessToken = jwtService.generateToken(userGet);
        return RefreshTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public UserDetailsResponse getUserDetails() throws CustomException {
        var user = UserFromSecurityContext.get();
        return UserDetailsResponse.fromFitnessFolks(user);

    }
}
