package com.a2.swifting_fitness.features.diet.service;

import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.repository.DietRepository;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class DietService {
    final private DietRepository repository;
    final  private FileStorageService fileStorageService;


    public Page<Diet> getDietList(int pageSize, int pageNumber){
        var pageable=PageRequest.of(pageNumber,pageSize);
        return repository.findAll(pageable);
    }

    public  Diet addDiet(DietRequest request, MultipartFile image) throws IOException, CustomException {
        var fileStorage=fileStorageService.saveFile(image, FileType.IMAGE,true);
        return  repository.save(
                Diet.builder()
                        .name(request.getName())
                        .image(fileStorage)
                        .fat(request.getFat())
                        .calories(request.getCalories())
                        .carbs(request.getCarbs())
                        .protein(request.getProtein())
                        .build()
        );
    }
}
