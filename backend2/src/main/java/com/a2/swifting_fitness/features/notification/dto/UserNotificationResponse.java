package com.a2.swifting_fitness.features.notification.dto;

import com.a2.swifting_fitness.features.notification.entity.UserNotification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
@Data
@AllArgsConstructor
@Builder
public class UserNotificationResponse {
    private String image;
    private String title;
    private  String description;
    private  String onClickedGoTo;
    private  String onClickedGoToData;
    private boolean onClickGoToWeb;
    private String body;
    private String topic;
    private Instant createdAt;
    private  boolean seen;

    static  public UserNotificationResponse fromUserNotification(UserNotification userNotification){
        return  UserNotificationResponse.builder()
                .image(userNotification.getImage())
                .title(userNotification.getTitle())
                .description(userNotification.getDescription())
                .onClickedGoTo(userNotification.getOnClickedGoTo())
                .onClickedGoToData(userNotification.getOnClickedGoToData())
                .onClickGoToWeb(userNotification.isOnClickGoToWeb())
                .body(userNotification.getBody())
                .topic(userNotification.getTopic())
                .createdAt(userNotification.getCreatedAt())
                .seen(userNotification.isSeen())
                .build();
    }
}
