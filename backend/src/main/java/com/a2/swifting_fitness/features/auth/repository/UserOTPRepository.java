package com.a2.swifting_fitness.features.auth.repository;
import com.a2.swifting_fitness.features.auth.entity.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOTPRepository extends JpaRepository<UserOTP, Integer> {
    @Query("SELECT p FROM UserOTP p WHERE p.user.id = :userId")
    List<UserOTP> findByUserId(@Param("userId") int userId);
}
