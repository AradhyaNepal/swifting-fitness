package com.a2.swifting_fitness.features.notification.repository;
import com.a2.swifting_fitness.features.notification.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification,String> {
}
