package com.a2.swifting_fitness.features.notification.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSeenRequest {
    @NotEmpty(message = StringConstants.emailRequired)
    private String notificationId;
}
