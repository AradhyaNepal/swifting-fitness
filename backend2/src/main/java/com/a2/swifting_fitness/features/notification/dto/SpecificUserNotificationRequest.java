package com.a2.swifting_fitness.features.notification.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SpecificUserNotificationRequest {
    @NotEmpty(message = StringConstants.uiNavigateToCannotBeEmpty)
    private String uiNavigateTo;
    private String uiNavigateData;
    private String image;
    @NotEmpty(message = StringConstants.titleIsRequired)
    private String title;
    private String body;
    @NotNull(message = StringConstants.userUidIsRequired)
    private  String userUid;

    private String description;
    private  boolean onClickGoToWeb;


}
