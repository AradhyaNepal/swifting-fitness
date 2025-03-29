package com.a2.swifting_fitness.features.notification.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.notification.dto.AllNotificationRequest;
import com.a2.swifting_fitness.features.notification.dto.SpecificUserNotificationRequest;
import com.a2.swifting_fitness.features.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RequestMapping(value = "/api/v1/admin")
@RequiredArgsConstructor
@RestController
public class NotificationController {

    final NotificationService notificationService;
    @PostMapping(value = "send-notification-to-all")
    public GenericResponseEntity<Void> sendToAll(@RequestBody @Valid AllNotificationRequest request) throws CustomException, ExecutionException, InterruptedException {
        notificationService.sendToAllDevice(request);
        return GenericResponseEntity.successWithMessage(
                StringConstants.notificationSuccessfullySent);
    }

    @PostMapping(value = "send-notification-to-specific")
    public GenericResponseEntity<Void> sendToUser(@RequestBody @Valid SpecificUserNotificationRequest request) throws CustomException, ExecutionException, InterruptedException {
        notificationService.sendToSpecificUser(request);
        return GenericResponseEntity.successWithMessage(
                StringConstants.notificationSuccessfullySent);
    }
}
