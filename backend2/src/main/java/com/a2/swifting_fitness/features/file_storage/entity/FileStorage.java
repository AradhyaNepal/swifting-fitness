package com.a2.swifting_fitness.features.file_storage.entity;


import com.a2.swifting_fitness.common.enums.FileType;
import com.a2.swifting_fitness.features.auth.entity.Users;
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
@Table(name = "File_Storage")
public class FileStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String filePath;

    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

    @NotNull
    private String extension;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    @NotNull
    private Boolean isOpen;


}
