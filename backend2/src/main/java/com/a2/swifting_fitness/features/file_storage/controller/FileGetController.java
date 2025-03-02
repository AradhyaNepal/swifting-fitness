package com.a2.swifting_fitness.features.file_storage.controller;

import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(value = "/api/v1/file")
@RestController
@RequiredArgsConstructor
public class FileGetController {

    final FileStorageService fileStorageService;

    @SecurityRequirement(name = "Authorization")
    @SecurityRequirement(name = "Device-Id")
    @GetMapping(value = "/{uid}")
    ResponseEntity<?> getMyFile(@PathVariable("uid") String uid) throws CustomException, IOException {
        System.out.println("Hello");
        return  fileStorageService.getMyFile(uid);
    }
    @GetMapping(value = "open/{uid}")
    ResponseEntity<?> getOpenFile(@PathVariable("uid") String uid) throws CustomException, IOException {
        return  fileStorageService.getOpenFile(uid);
    }
}
