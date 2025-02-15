package com.a2.swifting_fitness.features.auth.repository;

import com.a2.swifting_fitness.features.auth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT p FROM Users p WHERE p.uid = :uid")
    Optional<Users> findByUId(@Param("uid") String uid);

    @Query("SELECT p FROM Users p WHERE p.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);



}
