package com.a2.swifting_fitness.features.auth.service;

import com.a2.swifting_fitness.common.enums.OTPPurpose;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.OTPResendLimitException;
import com.a2.swifting_fitness.common.model.EmailDetails;
import com.a2.swifting_fitness.common.service.EmailService;
import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import com.a2.swifting_fitness.features.auth.entity.UserOTP;
import com.a2.swifting_fitness.features.auth.repository.UserOTPRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class OTPService {
    final private EmailService emailService;
    final private UserOTPRepository otpRepository;
    final private PasswordEncoder passwordEncoder;

    public void generateAndSendOTP(FitnessFolks user, OTPPurpose purpose) throws CustomException {
        if (!user.isAccountNonLocked()) {
            throw new CustomException(StringConstants.userLockedCannotContinue);
        }
        StringBuilder otp = getSecureOTP();
        var now = Instant.now();
        var oldOTP = checkNewOTPLimitAndExpireOldOTP(user, now, purpose);
        otpRepository.saveAll(oldOTP);
        otpRepository.save(UserOTP.builder()
                .purpose(purpose)
                .user(user)
                .createdAt(now)
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


    private List<UserOTP> checkNewOTPLimitAndExpireOldOTP(FitnessFolks user, Instant now, OTPPurpose purpose) throws CustomException {
        var sortedOTP = otpRepository.sortedOTP(user.getId(), purpose);

        mayThrowLotsOfOTPOnSpecificHour(sortedOTP, now);
        for (var e : sortedOTP) {

            e.setExpiry(now);
        }
        return sortedOTP;
    }

    private void mayThrowLotsOfOTPOnSpecificHour(List<UserOTP> sortedOTP, Instant now) throws CustomException {
        var latestOTP = sortedOTP
                .stream().filter(e -> e.getCreatedAt().isAfter(now.minus(12, ChronoUnit.HOURS))).toList();

        if (latestOTP.size() > 9) {
            throw new CustomException(StringConstants.sendingOTPLocked);
        }
        for (var e : latestOTP) {
            if (e.getCreatedAt().plus(1, ChronoUnit.MINUTES).isAfter(now)) {
                throw new OTPResendLimitException();
            }
        }
    }

    private StringBuilder getSecureOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit (0-9)
        }
        return otp;
    }


    public boolean otpIsCorrect(FitnessFolks fitnessFolks, String enteredOTP, OTPPurpose purpose) throws CustomException {
        if (!fitnessFolks.isAccountNonLocked()) {
            throw new CustomException(StringConstants.userLockedCannotContinue);
        }
        Instant now = Instant.now();
        var notExpiredOtp = otpRepository.sortedOTP(fitnessFolks.getId(), purpose)
                .stream()
                .filter(
                        e -> e.getExpiry().isAfter(now) && passwordEncoder.matches(enteredOTP, e.getOtpEncoded())
                ).toList();

        var isValid = !notExpiredOtp.isEmpty();
        if (isValid) {
            notExpiredOtp.forEach(e -> e.setExpiry(now));
            otpRepository.saveAll(notExpiredOtp);
        }
        return isValid;
    }

    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(fixedDelay = 10000)
    public void deleteExpiredOTP() {
        System.out.println("Deleting OTP Scheduler called");
        otpRepository.deleteAll(otpRepository.findAll().stream().filter(e -> e.getExpiry().plus(1, ChronoUnit.DAYS).isBefore(Instant.now())).toList());
    }
}
