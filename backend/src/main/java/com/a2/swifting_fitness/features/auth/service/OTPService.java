package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.model.EmailDetails;
import com.a2.swifting_fitness.common.service.EmailService;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.entity.UserOTP;
import com.a2.swifting_fitness.features.auth.repository.UserOTPRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OTPService {
    final private EmailService emailService;
    final private UserOTPRepository otpRepository;
    final private PasswordEncoder passwordEncoder;

    public void generateAndSendOTP(FitnessFolks user) throws CustomException {
        if (!user.isAccountNonLocked()) {
            throw new CustomException("User account is locked due to suspicious requests. Please try again later.");
        }
        StringBuilder otp = getSecureOTP();
        var now = LocalDateTime.now();
        var oldOTP = checkAndExpireOldUser(user, now);
        otpRepository.saveAll(oldOTP);
        otpRepository.save(UserOTP.builder()
                .user(user)
                .expiry(now.plusMinutes(30))
                .otpEncoded(passwordEncoder.encode(otp.toString()))
                .build());
        emailService.sendSimpleMail(
                EmailDetails.builder().recipient(user.getEmail())
                        .subject("OTP")
                        .msgBody("Your OTP is " + otp)
                        .build()
        );

    }

    private List<UserOTP> checkAndExpireOldUser(FitnessFolks user, LocalDateTime now) throws CustomException {
        var oldOTP = otpRepository.findByUserId(user.getId())
                .stream().filter(e -> e.getExpiry().isAfter(now.minusMinutes(60))).toList();
        if (oldOTP.size() > 5) {
            throw new CustomException("Lots of OTP sent. Please try again later after few hours.");
        }
        for (var e : oldOTP) {
            e.setExpiry(now);
        }
        return oldOTP;
    }

    private StringBuilder getSecureOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit (0-9)
        }
        return otp;
    }

    public boolean otpIsCorrect(FitnessFolks fitnessFolks, String enteredOTP) {
        var otpEncoded = passwordEncoder.encode(enteredOTP);
        var notExpiredOtp = otpRepository.findByUserId(fitnessFolks.getId())
                .stream()
                .filter(
                        e -> e.getExpiry().isAfter(LocalDateTime.now()) && otpEncoded.equals(e.getOtpEncoded())
                );

        return notExpiredOtp.findAny().isPresent();
    }
}