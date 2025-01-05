package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.features.auth.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.*;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    final private FitnessFolksRepository userRepo;
    final private AuthenticationManager authManager;
    final private JwtService jwtService;
    final private OTPService otpService;
    final private PasswordEncoder passwordEncoder;


    public AuthenticatedResponse login(LoginRequest request) throws CustomException {
        try {
            var user = userRepo.findByEmail(request.getEmail());
            try {
                var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if (authentication.isAuthenticated()) {
                    if (user.isPresent()) {
                        var accessToken = jwtService.generateToken(user.get());
                        return AuthenticatedResponse.builder().accessToken(accessToken).build();
                    } else {

                        throw new CustomException(StringConstants.noUserFromThatUsername);

                    }
                } else {
                    throw new CustomException(StringConstants.invalidCredentials);
                }
            } catch (AuthenticationException se) {
                if (user.isPresent()) {
                    var userGet = user.get();
                    var wrongAttempts = userGet.getWrongAttempts() + 1;
                    var now = LocalDateTime.now();
                    var blockedTill = userGet.getIsBlockedTill();
                    if (blockedTill == null || blockedTill.isAfter(now)) {
                        throw new CustomException(se.getMessage());
                    }
                    if (wrongAttempts >= 5) {
                        userGet.setWrongAttempts(0);
                        userGet.setIsBlockedTill(LocalDateTime.now().plusHours(2));
                    } else {
                        userGet.setWrongAttempts(wrongAttempts);
                    }
                    userRepo.save(userGet);


                }
                throw new CustomException(se.getMessage());
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }


    }

    public void register(RegisterRequest request) throws CustomException {
        try {
            var existingUser = userRepo.preexistingUserToRegister(request.getEmail());
            if (existingUser.isPresent()) {
                throw new CustomException(StringConstants.emailAlreadyExist);
            }
            var user = userRepo.save(FitnessFolks.builder()
                    .fullName(request.getFullName())
                    .age(request.getAge())
                    .email(request.getEmail())
                    .gender(request.getGender())
                    .build());
            otpService.generateAndSendOTP(user);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public void verifyOTP(VerifyOTPRequest request) throws CustomException {
        try {
            var user = userRepo.findByEmail(request.getEmail());
            if (user.isPresent()) {
                var userGet = user.get();
                var otpIsValid = otpService.otpIsCorrect(userGet, request.getOtp(), false);
                updateWrongAttemptAndUpdateUser(userGet, otpIsValid);
            } else {
                throw new CustomException(StringConstants.noUserFromThatUsername);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private void updateWrongAttemptAndUpdateUser(FitnessFolks userGet, boolean isValid) throws CustomException {
        if (isValid) {
            userGet.setWrongAttempts(0);
            userGet.setIsBlockedTill(null);
            userRepo.save(userGet);
            return;
        }
        var wrongAttempt = userGet.getWrongAttempts() + 1;
        var totalAttempt = 5;
        if (wrongAttempt == totalAttempt) {
            userGet.setIsBlockedTill(LocalDateTime.now().plusDays(1));
            userGet.setWrongAttempts(0);
            userRepo.save(userGet);
            throw new CustomException(StringConstants.blockingUserDueToWrongTrial);
        } else {
            userGet.setWrongAttempts(wrongAttempt);
            userRepo.save(userGet);
            throw new CustomException(StringConstants.invalidOTP + (wrongAttempt == totalAttempt - 1 ? " Last One Attempt Left" : ""));

        }
    }


    public void sendOTPToEmail(SendOTPToEmailRequest request) throws CustomException {
        try {


            var user = userRepo.findByEmail(request.getEmail());
            if (user.isPresent()) {
                otpService.generateAndSendOTP(user.get());
            } else {
                throw new CustomException(StringConstants.noUserFromThatUsername);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public AuthenticatedResponse setRegisterPassword(SetPasswordRequest request) throws CustomException {
        try {


            var accessToken = jwtService.generateToken(setPassword(request));
            return AuthenticatedResponse.builder().accessToken(accessToken).build();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public FitnessFolks setPassword(SetPasswordRequest request) throws CustomException {
        try {
            var user = userRepo.findByEmail(request.getEmail());
            if (user.isPresent()) {
                var userGet = user.get();
                var otpIsValid = otpService.otpIsCorrect(userGet, request.getOtp(), true);
                if (otpIsValid) {
                    userGet.setPassword(passwordEncoder.encode(request.getPassword()));
                    updateWrongAttemptAndUpdateUser(userGet, true);
                    return userGet;
                } else {
                    updateWrongAttemptAndUpdateUser(userGet, false);
                    throw new CustomException(StringConstants.invalidOTP);
                }

            } else {
                throw new CustomException(StringConstants.noUserFromThatUsername);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


}
