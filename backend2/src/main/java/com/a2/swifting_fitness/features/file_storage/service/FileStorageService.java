package com.a2.swifting_fitness.features.file_storage.service;


import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.features.file_storage.entity.FileStorage;
import com.a2.swifting_fitness.features.file_storage.repository.FileStorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

@Service
@AllArgsConstructor
public class FileStorageService {

    final FileStorageRepository repository;
    @Value("${file_path}")
    private  String IMAGE_PATH;
    public FileStorage saveFile(MultipartFile file, FileType fileType,boolean isOpen) throws IOException, CustomException {
        var extension=file.getContentType();
        var filePath= IMAGE_PATH+"/"+Instant.now().toString()+"."+extension;
        file.transferTo(new File(filePath));
        var user= UserFromSecurityContext.get();
        return repository.save(
                FileStorage.builder()
                        .filePath(filePath)
                        .fileType(fileType)
                        .extension(extension)
                        .isOpen(isOpen)
                        .createdBy(user)
                        .build()
        );
    }
}
