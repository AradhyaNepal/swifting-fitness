package com.a2.swifting_fitness.features.auth.repository;

import com.a2.swifting_fitness.features.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Query("SELECT p FROM RefreshToken p WHERE p.token = :token")
    Optional<RefreshToken> findByToken(@Param("token") String token);
}
