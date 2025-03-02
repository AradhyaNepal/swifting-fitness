package com.a2.swifting_fitness.features.file_storage.service;

import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.common.utils.UserFromSecurityContext;
import com.a2.swifting_fitness.features.file_storage.entity.FileStorage;
import com.a2.swifting_fitness.features.file_storage.repository.FileStorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class FileStorageService {

    final FileStorageRepository repository;

    private final Path rootLocation = Paths.get("uploads");

    public FileStorage saveFile(MultipartFile file, FileType fileType,boolean isOpen) throws IOException, CustomException {
       if( !Files.exists(rootLocation)){
           Files.createDirectories(rootLocation);
       }
        var fileName=file.getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        var extension=fileName.substring(fileName.lastIndexOf(".") + 1);
        var destinationFileName=currentTime+"."+extension;
        Path destinationFile = rootLocation.resolve(destinationFileName).normalize().toAbsolutePath();
        file.transferTo(destinationFile);
        var user= UserFromSecurityContext.get();
        return repository.save(
                FileStorage.builder()
                        .filePath(destinationFileName)
                        .fileType(fileType)
                        .extension(extension)
                        .isOpen(isOpen)
                        .createdBy(user)
                        .build()
        );
    }


    public ResponseEntity<byte[]> getOpenFile(String uid) throws CustomException, IOException {
        var value=repository.findById(uid);
        if(value.isEmpty()){
            throw  new CustomException("No image found");
        }else{
            var valueGet=value.get();
            if(!valueGet.getIsOpen()){
                throw  new CustomException("Image is not open", HttpStatus.FORBIDDEN);
            }
            var file = rootLocation.resolve(valueGet.getFilePath()).normalize().toAbsolutePath().toFile();
            if (!file.exists()) {
                throw new CustomException("No image found",HttpStatus.NOT_FOUND);
            }

            byte[] imageBytes = Files.readAllBytes(file.toPath());
            String contentType = Files.probeContentType(file.toPath());

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);

        }
    }
}
