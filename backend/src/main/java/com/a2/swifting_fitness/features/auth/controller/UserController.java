package com.a2.swifting_fitness.features.auth.controller;

import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.constants.StringConstants;
import com.a2.swifting_fitness.common.model.GenericResponseEntity;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenRequest;
import com.a2.swifting_fitness.features.auth.dto.RefreshTokenResponse;
import com.a2.swifting_fitness.features.auth.dto.UserDetailsResponse;
import com.a2.swifting_fitness.features.auth.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
