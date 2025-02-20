package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class AdminLoginRequest extends LoginRequest {

    @NotEmpty(message = StringConstants.deviceIdRequired)
    final  String deviceId;
}
