package com.a2.swifting_fitness.features.workout.repository;

import com.a2.swifting_fitness.features.diet.entity.Diet;
import com.a2.swifting_fitness.features.workout.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout,Integer> {}
