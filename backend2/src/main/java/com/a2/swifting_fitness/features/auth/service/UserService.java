package com.a2.swifting_fitness.features.auth.service;


import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.common.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenRequest;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenResponse;
import com.a2.swifting_fitness.features.auth.dto.UserDetailsResponse;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import com.a2.swifting_fitness.features.auth.repository.RefreshTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    final FitnessFolksRepository userRepo;
    final RefreshTokenService refreshTokenService;
    final JwtService jwtService;
    final BlockUserService blockUserService;

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws CustomException {
        try {
            String uid;
            try {
                uid = jwtService.extractUID(request.getAccessToken());
            } catch (ExpiredJwtException e) {
                uid = e.getClaims().getSubject();
            }
            var user = userRepo.findByUId(uid);
            if (user.isPresent()) {
                var userGet = user.get();
                var newToken = refreshTokenService.verifyThenDeleteOrCreateNew(request.getRefreshToken(), userGet);
                var accessToken = jwtService.generateToken(userGet);
                return RefreshTokenResponse.builder().refreshToken(newToken.getToken()).accessToken(accessToken).build();
            } else {
                throw new CustomException(StringConstants.invalidToken);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public UserDetailsResponse getUserDetails() throws CustomException {
        var user = UserFromSecurityContext.get();
        return UserDetailsResponse.fromFitnessFolks(user);

    }
}
