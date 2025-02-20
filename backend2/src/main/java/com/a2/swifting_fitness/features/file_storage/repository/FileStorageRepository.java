package com.a2.swifting_fitness.features.file_storage.repository;

import com.a2.swifting_fitness.features.file_storage.entity.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,String> {
}
