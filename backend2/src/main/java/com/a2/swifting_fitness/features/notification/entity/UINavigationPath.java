package com.a2.swifting_fitness.features.notification.entity;

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
@Table(name = "UINavigationPath")
public class UINavigationPath {
    @Id
    private String code;

    @NotNull
    private String documentation;

}
