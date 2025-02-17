package com.a2.swifting_fitness.features.file_storage.service;


import com.a2.swifting_fitness.common.constants.StringConstants;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
@AllArgsConstructor
public class FileStorageService {

    final FileStorageRepository repository;
//    @Value("${file_path}")
    private final Path rootLocation = Paths.get("uploads");

    public FileStorage saveFile(MultipartFile file, FileType fileType,boolean isOpen) throws IOException, CustomException {
       if( !Files.exists(rootLocation)){
           Files.createDirectories(rootLocation);
       }
        var fileName=file.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        var extension=fileName.substring(fileName.lastIndexOf(".") + 1);
        Path destinationFile = rootLocation.resolve(currentTime+"."+extension).normalize().toAbsolutePath();
        file.transferTo(destinationFile);
        var user= UserFromSecurityContext.get();
        return repository.save(
                FileStorage.builder()
                        .filePath(destinationFile.toString())
                        .fileType(fileType)
                        .extension(extension)
                        .isOpen(isOpen)
                        .createdBy(user)
                        .build()
        );
    }
}
