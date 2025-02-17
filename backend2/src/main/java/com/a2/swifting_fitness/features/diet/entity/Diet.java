package com.a2.swifting_fitness.features.diet.entity;

import com.a2.swifting_fitness.features.file_storage.entity.FileStorage;
import jakarta.persistence.*;
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
    @OneToOne
    @JoinColumn(name = "image", nullable = false)
    private FileStorage image;

    @NotNull
    private String name;

    @NotNull
    private int calories;

    @NotNull
    private int protein;

    private Integer fat;

    private Integer carbs;
}
