package com.a2.swifting_fitness.features.auth.entity;

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
    private Instant expiry;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private FitnessFolks user;

}
