package com.a2.swifting_fitness.features.workout.service;

import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.common.exception.CustomException;
import com.a2.swifting_fitness.features.diet.dto.DietRequest;
import com.a2.swifting_fitness.features.diet.dto.DietResponse;
import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.mapper.DietToDietResponse;
import com.a2.swifting_fitness.features.diet.repository.DietRepository;
import com.a2.swifting_fitness.features.file_storage.service.FileStorageService;
import com.a2.swifting_fitness.features.workout.dto.WorkoutRequest;
import com.a2.swifting_fitness.features.workout.dto.WorkoutResponse;
import com.a2.swifting_fitness.features.workout.entity.Workout;
import com.a2.swifting_fitness.features.workout.mapper.WorkoutToWorkoutResponse;
import com.a2.swifting_fitness.features.workout.repository.WorkoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class WorkoutService {
    final private WorkoutRepository repository;
    final  private FileStorageService fileStorageService;


    public Page<WorkoutResponse> getDietList(int pageSize, int pageNumber){
        var pageable=PageRequest.of(pageNumber,pageSize);
        var data= repository.findAll(pageable);
        return data.map(WorkoutToWorkoutResponse::map);
    }

    public WorkoutResponse addDiet(WorkoutRequest request) throws IOException, CustomException {
        var image=fileStorageService.saveFile(request.getImage(), FileType.IMAGE,true);
        var video=fileStorageService.saveFile(request.getVideo(), FileType.VIDEO,true);
        var workout=  repository.save(
                Workout.builder()
                        .name(request.getName())
                        .image(image)
                        .video(video)
                        .calories(request.getCalories())
                        .durationMin(request.getDurationMin())
                        .build()
        );
        return  WorkoutToWorkoutResponse.map(workout);
    }
}
