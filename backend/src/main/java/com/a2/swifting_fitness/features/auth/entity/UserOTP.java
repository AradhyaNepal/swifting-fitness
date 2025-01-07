package com.a2.swifting_fitness.features.auth.entity;

import com.a2.swifting_fitness.common.enums.Gender;
import com.a2.swifting_fitness.common.enums.OTPPurpose;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserOTP")
public class UserOTP {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private  String otpEncoded;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OTPPurpose purpose;

    @NotNull
    private Instant expiry;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private FitnessFolks user;

}
