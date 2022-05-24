package com.mmenshikov.lyukinafashion.storage.repository;

import com.mmenshikov.lyukinafashion.domain.entity.StorageObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StorageObjectRepository extends JpaRepository<StorageObject, Long> {
    List<StorageObject> findAllByProductId(Long productId);
}
