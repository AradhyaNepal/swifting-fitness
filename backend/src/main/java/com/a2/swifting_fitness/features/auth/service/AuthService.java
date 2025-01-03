package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.features.auth.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.AuthenticatedResponse;
import com.a2.swifting_fitness.features.auth.dto.LoginRequest;
import com.a2.swifting_fitness.features.auth.dto.RegisterRequest;
import com.a2.swifting_fitness.features.auth.dto.VerifyOTPRequest;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthService {
    final private FitnessFolksRepository fitnessFolksRepo;
    final private AuthenticationManager authManager;
    final private JwtService jwtService;
    final private OTPService otpService;


    public AuthenticatedResponse login(LoginRequest request) throws CustomException {
        var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            var user = fitnessFolksRepo.findByEmail(request.getEmail());
            if (user.isPresent()) {
                var accessToken = jwtService.generateToken(user.get());
                return AuthenticatedResponse.builder().accessToken(accessToken).build();
            } else {
                throw new CustomException("Cannot find user details");

            }
        } else {
            throw new CustomException("Invalid Credentials");
        }
    }

    public AuthenticatedResponse register(RegisterRequest request) throws CustomException {
        var existingUser = fitnessFolksRepo.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new CustomException("User with given email already exist");
        }
        var user = fitnessFolksRepo.save(FitnessFolks.builder()
                .fullName(request.getFullName())
                .age(request.getAge())
                .email(request.getEmail())
                .gender(request.getGender())
                .build());
        var accessToken = jwtService.generateToken(user);
        otpService.generateAndSendOTP(user);
        return AuthenticatedResponse.builder().accessToken(accessToken).build();
    }

    public boolean verifyPassword(VerifyOTPRequest request) throws CustomException {
        var user = fitnessFolksRepo.findByEmail(request.getEmail());
        if (user.isPresent()) {
            var userGet = user.get();
            var otpIsValid = otpService.otpIsCorrect(userGet, request.getOtp());
            if (otpIsValid) return true;
            var wrongAttempt = userGet.getWrongAttempts() + 1;
            var totalAttempt = 5;
            if (wrongAttempt == totalAttempt) {
                userGet.setIsBlockedTill(LocalDateTime.now().plusDays(1));
                userGet.setWrongAttempts(0);
                fitnessFolksRepo.save(userGet);
                throw new CustomException("Lots of wrong trial. Your account is blocked for 1 day");
            } else {
                userGet.setWrongAttempts(wrongAttempt);
                fitnessFolksRepo.save(userGet);
                throw new CustomException("Invalid OTP." + (wrongAttempt == totalAttempt - 1 ? " Last One Attempt Left" : ""));

            }
        } else {
            throw new CustomException(StringConstants.noUserFromThatUsername);
        }
    }




}
