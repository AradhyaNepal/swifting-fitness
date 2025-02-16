package com.a2.swifting_fitness.features.diet.repository;

import com.a2.swifting_fitness.features.diet.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DietRepository extends JpaRepository<Diet,Integer> {}
