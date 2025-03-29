package com.a2.swifting_fitness.features.auth.service;


import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.common.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.*;
import com.a2.swifting_fitness.features.auth.repository.UsersRepository;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class UserService {
    final UsersRepository userRepo;
    final RefreshTokenService refreshTokenService;
    final JwtService jwtService;
    final BlockUserService blockUserService;
    final FileStorageService fileStorageService;

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

    public void logout(LogoutRequest request) throws CustomException {
        var user = UserFromSecurityContext.get();
        var fcmTokens=user.getFcmToken();
        fcmTokens.removeIf(e->e.equals(request.getFcmToken()));
        user.setFcmToken(fcmTokens);
        userRepo.save(user);
    }



    public  UserDetailsResponse updateProfile(ProfileUpdateRequest request) throws CustomException, IOException {
        var user = UserFromSecurityContext.get();
     user.setFirstName(request.getFirstName());
     user.setLastName(request.getSecondName());
       var profile= fileStorageService.saveFile(request.getProfilePicture(), FileType.IMAGE,false);
     user.setProfile(profile);
     userRepo.save(user);
        return UserDetailsResponse.fromFitnessFolks(user);

    }
}
