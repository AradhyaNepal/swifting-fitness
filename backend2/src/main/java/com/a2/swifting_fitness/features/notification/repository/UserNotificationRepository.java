package com.a2.swifting_fitness.features.notification.repository;
import com.a2.swifting_fitness.features.notification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,String> {

    @Query("SELECT p FROM UserNotification p WHERE p.user.id = :userId")
    List<UserNotification> getUserNotification(@Param("userId") int userId);

}
