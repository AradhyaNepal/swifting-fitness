package com.a2.swifting_fitness.features.notification.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AllNotificationRequest {
    @NotEmpty(message = StringConstants.uiNavigateToCannotBeEmpty)
    private String uiNavigateTo;
    private String uiNavigateData;
    private String image;
    @NotEmpty(message = StringConstants.titleIsRequired)
    private String title;
    private String body;


}
