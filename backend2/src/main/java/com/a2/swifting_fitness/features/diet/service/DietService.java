package com.a2.swifting_fitness.features.diet.service;

import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.diet.repository.DietRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DietService {
    final private DietRepository repository;


    public Page<Diet> getDietList(int pageSize, int pageNumber){
        var pageable=PageRequest.of(pageNumber,pageSize);
        return repository.findAll(pageable);
    }

    public  Diet addDiet(){

    }
}
