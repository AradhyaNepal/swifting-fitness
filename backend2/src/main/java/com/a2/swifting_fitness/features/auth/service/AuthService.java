package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.enums.UserRole;
import com.a2.swifting_fitness.common.enums.UserTier;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.config.JwtService;
import com.a2.swifting_fitness.common.exception.OTPResendLimitException;
import com.a2.swifting_fitness.features.auth.dto.*;
import com.a2.swifting_fitness.features.auth.entity.Users;
import com.a2.swifting_fitness.features.auth.repository.UsersRepository;
import com.a2.swifting_fitness.features.notification.repository.FCMRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {
    final private UsersRepository userRepo;
    final private AuthenticationManager authManager;
    final private JwtService jwtService;
    final private OTPService otpService;
    final private PasswordEncoder passwordEncoder;
    final private RefreshTokenService refreshTokenService;
    final private BlockUserService blockUserService;
    final private FCMRepository fcmRepository;


    public AuthenticatedResponse login(LoginRequest request) throws CustomException {
        try {
            var user = userRepo.findByEmail(request.getEmail());
            try {
                var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
                if (authentication.isAuthenticated()) {
                    if (user.isPresent()) {
                        var userGet = user.get();
                        if(request.getFcmToken()!=null){
                            userGet.getFcmToken().add(request.getFcmToken());
                            fcmRepository.subscribe(request.getFcmToken(),"all");
                        }
                        checkForAdmin(request,userGet);
                        blockUserService.removeUserAllBlockageAndSave(userGet, userRepo);
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
                    message = StringConstants.accountSetupNotComplete;
                    ;
                } else if (se instanceof LockedException) {

                    message = StringConstants.accountLocked;
                } else if (se instanceof BadCredentialsException) {
                    message = StringConstants.invalidUsernamePassword;
                }

                if (user.isPresent()) {

                    var userGet = user.get();
                    var now = Instant.now();
                    var blockedTill = userGet.getIsBlockedTill();
                    if (blockedTill != null && blockedTill.isAfter(now)) {
                        throw new CustomException(message, HttpStatus.FORBIDDEN, extraFlag);
                    }
                    blockUserService.setBlockUser(userGet);
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

    private void checkForAdmin(LoginRequest loginRequest,Users users) throws CustomException {
        if(loginRequest instanceof AdminLoginRequest loginCasted){
            if(users.getDeviceId()==null || !loginCasted.getDeviceId().equals(users.getDeviceId())){
                var map=new HashMap<String,Object>();
                map.put("deviceIDNotSet",true);
                throw  new CustomException("Please setup device id to continue",HttpStatus.FORBIDDEN,map);
            }
        }
    }

    public String register(RegisterRequest request) throws CustomException {
        try {
            var existingUser = userRepo.findByEmail(request.getEmail());
            String message = StringConstants.registerSuccessfully;
            Users user = new Users();
            if (existingUser.isPresent()) {
                user = existingUser.get();
                if (user.isEnabled()) {
                    throw new CustomException(StringConstants.emailAlreadyExist);
                } else {
                    message = StringConstants.reRegisterSuccessfully;
                }
            }
            user.setFirstName(request.getFirstName() );
            user.setLastName(request.getSecondName() );
            user.setUserTier(UserTier.NEWBIE);
            user.setEmail(request.getEmail());
            user.setAccountVerified(false);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(UserRole.user);
            user = userRepo.save(user);
            otpService.generateAndSendOTP(user, OTPPurpose.REGISTER);
            return message;
        } catch (OTPResendLimitException e) {
            return StringConstants.reRegisterSuccessfullyReuseOTP;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
    }

    public AuthenticatedResponse registerVerifyOTP(VerifyOTPRequest request) throws CustomException {
        try {
            var user = verifyOTP(
                    request.getEmail(),
                    request.getOtp(),
                    OTPPurpose.REGISTER
            );
            if (user.getAccountVerified()) {
                throw new CustomException(StringConstants.alreadyAccountVerified);
            }
            user.setAccountVerified(true);
            userRepo.save(user);
            var refreshToken = refreshTokenService.createRefreshToken(user).getToken();
            var accessToken = jwtService.generateToken(user);
            return AuthenticatedResponse.builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .build();

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public AuthenticatedResponse adminVerifySetDeviceID(AdminNewDeviceVerifyRequest request) throws CustomException {
        try {
            var user = verifyOTP(
                    request.getEmail(),
                    request.getOtp(),
                    OTPPurpose.ADMIN_DEVICE_ID
            );
            user.setAccountVerified(true);
            user.setDeviceId(request.getDeviceId());
            userRepo.save(user);
            var refreshToken = refreshTokenService.createRefreshToken(user).getToken();
            var accessToken = jwtService.generateToken(user);
            return AuthenticatedResponse.builder()
                    .refreshToken(refreshToken)
                    .accessToken(accessToken)
                    .build();

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    public void forgotPasswordVerifyAndSetPassword(VerifyOTPAndSetPasswordRequest request) throws CustomException {
        try {
            var user = verifyOTP(request.getEmail(), request.getOtp(), OTPPurpose.FORGOT_PASSWORD);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepo.save(user);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }

    }

    private Users verifyOTP(String email, String otp, OTPPurpose purpose) throws CustomException {
        try {
            var user = userRepo.findByEmail(email);
            if (user.isPresent()) {
                var userGet = user.get();
                var otpIsValid = otpService.otpIsCorrect(userGet, otp, purpose);
                if (otpIsValid) {
                    blockUserService.removeUserAllBlockageAndSave(userGet, userRepo);
                } else {
                    blockUserService.setBlockUser(userGet);
                    throw new CustomException(StringConstants.invalidOTP);

                }

                return userGet;
            } else {
                throw new CustomException(StringConstants.noUserFromThatUsername);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    public void sendOTPToEmail(SendOTPToEmailRequest request, OTPPurpose purpose) throws CustomException {
        try {


            var user = userRepo.findByEmail(request.getEmail());
            if (user.isPresent()) {
                var userGet = user.get();
                if (purpose == OTPPurpose.REGISTER && userGet.getAccountVerified()) {
                    throw new CustomException(StringConstants.alreadyAccountVerified);
                }
                otpService.generateAndSendOTP(userGet, purpose);
            } else {
                throw new CustomException(StringConstants.noUserFromThatUsername);
            }
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


}
