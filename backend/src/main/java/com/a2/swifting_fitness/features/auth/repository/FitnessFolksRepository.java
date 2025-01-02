package com.a2.swifting_fitness.features.auth.repository;

import com.a2.swifting_fitness.features.auth.entity.FitnessFolks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FitnessFolksRepository extends JpaRepository<FitnessFolks, Integer> {
    @Query("SELECT p FROM Players p WHERE p.uid = :uid")
    Optional<FitnessFolks> findByUId(@Param("uid") String uid);

    @Query("SELECT p FROM Players p WHERE p.email = :email")
    Optional<FitnessFolks> findByEmail(@Param("email") String email);

}
