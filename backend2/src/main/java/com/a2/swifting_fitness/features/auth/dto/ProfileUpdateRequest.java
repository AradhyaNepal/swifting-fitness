package com.a2.swifting_fitness.features.auth.dto;

import com.a2.swifting_fitness.common.constants.StringConstants;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Data
public class ProfileUpdateRequest {
    @NotEmpty(message = StringConstants.firstNameRequired)
    private String firstName;

    @NotEmpty(message = StringConstants.secondNameRequired)
    private String secondName;


    private MultipartFile profilePicture;

}
