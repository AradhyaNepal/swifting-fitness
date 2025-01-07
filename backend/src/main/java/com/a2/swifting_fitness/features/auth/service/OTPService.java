package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.CustomException;
import com.a2.swifting_fitness.common.StringConstants;
import com.a2.swifting_fitness.common.model.EmailDetails;
import com.a2.swifting_fitness.common.service.EmailService;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.entity.UserOTP;
import com.a2.swifting_fitness.features.auth.repository.UserOTPRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class OTPService {
    final private EmailService emailService;
    final private UserOTPRepository otpRepository;
    final private PasswordEncoder passwordEncoder;

    public void generateAndSendOTP(FitnessFolks user) throws CustomException {
        if (!user.isAccountNonLocked()) {
            throw new CustomException(StringConstants.userLockedCannotContinue);
        }
        StringBuilder otp = getSecureOTP();
        var now = Instant.now();
        var oldOTP = checkAndExpireOldUser(user, now);
        otpRepository.saveAll(oldOTP);
        otpRepository.save(UserOTP.builder()
                .user(user)
                .expiry(now.plus(30, ChronoUnit.MINUTES))
                .otpEncoded(passwordEncoder.encode(otp.toString()))
                .build());
        emailService.sendSimpleMail(
                EmailDetails.builder().recipient(user.getEmail())
                        .subject("OTP")
                        .msgBody("Your OTP is " + otp)
                        .build()
        );

    }

    private List<UserOTP> checkAndExpireOldUser(FitnessFolks user, Instant now) throws CustomException {
        var oldOTP = otpRepository.findByUserId(user.getId())
                .stream().filter(e -> e.getExpiry().isAfter(now.plus(60, ChronoUnit.MINUTES))).toList();
        if (oldOTP.size() > 5) {
            throw new CustomException(StringConstants.sendingOTPLocked);
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

    public boolean otpIsCorrect(FitnessFolks fitnessFolks, String enteredOTP, boolean expireIfValid) {
        Instant now = Instant.now();
        var notExpiredOtp = otpRepository.findByUserId(fitnessFolks.getId())
                .stream()
                .filter(
                        e -> e.getExpiry().isAfter(now) && passwordEncoder.matches(enteredOTP, e.getOtpEncoded())
                ).toList();

        var isValid = !notExpiredOtp.isEmpty();
        if (isValid && expireIfValid) {
            notExpiredOtp.forEach(e -> e.setExpiry(now));
        }
        return isValid;
    }
}
