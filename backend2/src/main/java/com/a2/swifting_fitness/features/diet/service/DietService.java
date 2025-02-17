package com.a2.swifting_fitness.features.diet.service;

import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.mapper.DietToDietResponse;
import com.a2.swifting_fitness.features.diet.repository.DietRepository;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@AllArgsConstructor
public class DietService {
    final private DietRepository repository;
    final  private FileStorageService fileStorageService;


    public Page<DietResponse> getDietList(int pageSize, int pageNumber){
        var pageable=PageRequest.of(pageNumber,pageSize);
        var data= repository.findAll(pageable);
        return data.map(DietToDietResponse::map);
    }

    public DietResponse addDiet(DietRequest request) throws IOException, CustomException {
        var fileStorage=fileStorageService.saveFile(request.getImage(), FileType.IMAGE,true);
        var diet=  repository.save(
                Diet.builder()
                        .name(request.getName())
                        .image(fileStorage)
                        .fat(request.getFat())
                        .calories(request.getCalories())
                        .carbs(request.getCarbs())
                        .protein(request.getProtein())
                        .build()
        );
        return  DietToDietResponse.map(diet);
    }
}
