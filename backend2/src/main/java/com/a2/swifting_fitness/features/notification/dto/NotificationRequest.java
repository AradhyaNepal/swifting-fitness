package com.a2.swifting_fitness.features.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NotificationRequest {
    private String image;
    private String title;
    private String body;
    private String topic;
    private String token;
    private  String page;
    private  String data;
}
