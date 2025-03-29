package com.a2.swifting_fitness.features.notification.service;


import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.auth.repository.UsersRepository;
import com.a2.swifting_fitness.features.notification.dto.AllNotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.NotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.SpecificUserNotificationRequest;
import com.a2.swifting_fitness.features.notification.repository.FCMRepository;
import com.a2.swifting_fitness.features.notification.repository.UserNotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class NotificationService {
    final private UserNotificationRepository userNotificationRepository;
    final private FCMRepository fcmRepository;
    final private UsersRepository usersRepository;

    void sendToAllDevice(AllNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmRepository.sendMessageToToken(
                NotificationRequest.builder()
                        .topic("all")
                        .title(request.getTitle())
                        .body(request.getBody())
                        .image(request.getImage())
                        .page(request.getUiNavigateTo())
                        .data(request.getUiNavigateData())
                        .build()
        );
    }




    void sendToSpecificUser(SpecificUserNotificationRequest request) throws CustomException, ExecutionException, InterruptedException {
        var user = usersRepository.findByUId(request.getUserUid());
        if (user.isEmpty()) {
            throw new CustomException("User not found", HttpStatus.NOT_FOUND);
        }
        var userGet = user.get();
        for (var item : userGet.getFcmToken()) {
            fcmRepository.sendMessageToToken(
                    NotificationRequest.builder()
                            .token(item)
                            .title(request.getTitle())
                            .body(request.getBody())
                            .image(request.getImage())
                            .page(request.getUiNavigateTo())
                            .data(request.getUiNavigateData())
                            .build()
            );
        }

    }

    void sendToAuthenticatedUserTopic() {

    }
}
