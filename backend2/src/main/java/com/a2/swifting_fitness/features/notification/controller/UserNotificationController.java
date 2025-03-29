package com.a2.swifting_fitness.features.notification.controller;

import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.notification.dto.NotificationSeenRequest;
import com.a2.swifting_fitness.features.notification.dto.UserNotificationResponse;
import com.a2.swifting_fitness.features.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "Authorization")
public class UserNotificationController  {
    final NotificationService service;

    @GetMapping(value = "notifications")
    GenericResponseEntity<List<UserNotificationResponse>> getAllNotification(@PathParam(value = "pageSize") Integer pageSize, @PathParam(value = "pageNumber") Integer pageNumber) {
        return GenericResponseEntity.successWithPagination(service.getNotificationResponse(pageSize, pageNumber-1), StringConstants.notificationFetchedSuccessfully);
    }

    @PostMapping(value = "set-notification-seen")
    GenericResponseEntity<Void> setSeen(@Valid @RequestBody NotificationSeenRequest request) throws CustomException {
        service.setNotificationSeen(request.getNotificationId());
        return GenericResponseEntity.successWithMessage(StringConstants.notificationSuccessfullySetSeen);
    }

    @PostMapping(value = "set-all-notification-seen")
    GenericResponseEntity<Void> setAllNotificationSeen() throws CustomException {
        service.setAllSeen();
        return GenericResponseEntity.successWithMessage(StringConstants.notificationSuccessfullySetSeen);
    }
}
