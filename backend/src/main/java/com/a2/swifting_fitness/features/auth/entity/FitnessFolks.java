package com.a2.swifting_fitness.features.auth.entity;

import com.a2.swifting_fitness.common.enums.Gender;
import com.a2.swifting_fitness.common.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FitnessFolks")
public class FitnessFolks implements UserDetails {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String fullName;

    @NotNull
    private String email;


    private String profile;
    @NotNull
    private int age;
    @NotNull
    private Gender gender;

    private int wrongAttempts;
    private Instant isBlockedTill;


    public @NotNull String getUsername() {
        return uid;
    }


    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Column(unique = true, nullable = false, updatable = false)
    private String uid;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return List.of(new SimpleGrantedAuthority("ROLE_" + UserRole.user));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (isBlockedTill == null) return true;
        return Instant.now().isAfter(isBlockedTill);
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return password != null;
    }


    @PrePersist
    public void generateGameUid() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }
}
