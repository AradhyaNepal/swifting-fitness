package com.a2.swifting_fitness.features.diet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Diet")
public class Diet {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String image;

    @NotNull
    private String name;

    @NotNull
    private int calories;

    @NotNull
    private int protein;

    private int fat;

    private int carbs;
}
