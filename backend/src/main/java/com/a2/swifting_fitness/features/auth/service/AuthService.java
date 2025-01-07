package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.features.auth.config.JwtService;
import com.a2.swifting_fitness.features.auth.dto.*;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.repository.FitnessFolksRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    final private FitnessFolksRepository userRepo;
    final private AuthenticationManager authManager;
    final private JwtService jwtService;
    final private OTPService otpService;
    final private PasswordEncoder passwordEncoder;
    final private RefreshTokenService refreshTokenService;
    final  private BlockUserService blockUserService;


    public AuthenticatedResponse login(LoginRequest request) throws CustomException {
        try {
            var user = userRepo.findByEmail(request.getEmail());
            try {
                var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if (authentication.isAuthenticated()) {
                    if (user.isPresent()) {
                        var userGet = user.get();
                        var accessToken = jwtService.generateToken(userGet);
                        var refreshToken = refreshTokenService.createRefreshToken(userGet).getToken();
                        return AuthenticatedResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                    } else {

                        throw new CustomException(StringConstants.noUserFromThatUsername);

                    }
                } else {
                    throw new CustomException(StringConstants.invalidCredentials);
                }
            } catch (AuthenticationException se) {
                Map<String, Boolean> extraFlag = null;
                var message = se.getMessage();
                if (se instanceof DisabledException) {
                    extraFlag = new HashMap<>();
                    extraFlag.put("registrationNotCompleted", true);
                    message = "Your account setup isn't completed yet, due to which your account is currently disabled.";
                    ;
                } else if (se instanceof LockedException) {

                    message = "Your account has been locked for few hours. It might be due to lots of wrong attempts.";
                } else if (se instanceof BadCredentialsException) {
                    message = "Invalid username or password.";
                }

                if (user.isPresent()) {

                    var userGet = user.get();
                    var now = Instant.now();
                    var blockedTill = userGet.getIsBlockedTill();
                    if (blockedTill != null && blockedTill.isAfter(now)) {
                        throw new CustomException(message, HttpStatus.FORBIDDEN, extraFlag);
                    }

                    userRepo.save(userGet);


                }
                throw new CustomException(message, HttpStatus.FORBIDDEN, extraFlag);
            }
        } catch (CustomException e) {
            throw e;
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
            userGet.setIsBlockedTill(Instant.now().plus(1, ChronoUnit.DAYS));
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
            var user = setPassword(request);
            var accessToken = jwtService.generateToken(user);
            var refreshToken = refreshTokenService.createRefreshToken(user).getToken();
            return AuthenticatedResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
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
