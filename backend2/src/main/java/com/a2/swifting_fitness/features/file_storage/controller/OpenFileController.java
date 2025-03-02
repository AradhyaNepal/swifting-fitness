package com.a2.swifting_fitness.features.file_storage.controller;

import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(value = "/api/v1/open/file")
@RestController
@RequiredArgsConstructor
public class OpenFileController {

    final FileStorageService fileStorageService;
    @GetMapping()
    ResponseEntity<?> getImage(@PathParam("uid") String uid) throws CustomException, IOException {
        return  fileStorageService.getOpenFile(uid);
    }
}
