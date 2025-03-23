package com.a2.swifting_fitness.features.auth.entity;

import com.a2.swifting_fitness.common.enums.UserRole;
import com.a2.swifting_fitness.common.enums.UserTier;
import com.a2.swifting_fitness.features.file_storage.entity.FileStorage;
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
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "image")
    private FileStorage profile;


    private int healthyPercentage;
    @Enumerated(EnumType.STRING)
    private UserTier userTier;

    private int wrongAttempts;
    private Instant isBlockedTill;

    @NotNull
    private Boolean accountVerified;


    public @NotNull String getUsername() {
        return uid;
    }


    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole role;

    private  String deviceId;


    @Column(unique = true, nullable = false, updatable = false)
    private String uid;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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
        return accountVerified;
    }


    @PrePersist
    public void generateGameUid() {
        if (uid == null) {
            uid = UUID.randomUUID().toString();
        }
    }
}
