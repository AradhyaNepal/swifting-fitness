package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.LogoutRequest;
import com.a2.swifting_fitness.features.auth.dto.ProfileUpdateRequest;
import com.a2.swifting_fitness.features.auth.dto.UserDetailsResponse;
import com.a2.swifting_fitness.features.auth.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/api/v1/user")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Authorization")
public class UserController {

    final  private UserService userService;



    @GetMapping()
    public GenericResponseEntity<UserDetailsResponse> getUserDetails() throws CustomException {
        return GenericResponseEntity.successWithData(userService.getUserDetails(), StringConstants.userDetailsFetchedSuccessfully);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public GenericResponseEntity<UserDetailsResponse> updateProfile(@Valid @ModelAttribute ProfileUpdateRequest request) throws CustomException, IOException {
        return GenericResponseEntity.successWithData(userService.updateProfile(request), StringConstants.userProfileUpdated);
    }

    @PostMapping(value = "logout")
    public GenericResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request) throws CustomException {
        userService.logout(request);
        return GenericResponseEntity.successWithMessage( StringConstants.successfullyLoggedOut);
    }

}
