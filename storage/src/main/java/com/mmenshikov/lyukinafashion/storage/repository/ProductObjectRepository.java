package com.mmenshikov.lyukinafashion.storage.repository;

import com.mmenshikov.lyukinafashion.domain.entity.ProductObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductObjectRepository extends JpaRepository<ProductObject, Long> {
}
