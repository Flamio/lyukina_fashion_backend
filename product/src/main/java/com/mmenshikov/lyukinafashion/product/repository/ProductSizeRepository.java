package com.mmenshikov.lyukinafashion.product.repository;

import com.mmenshikov.lyukinafashion.domain.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
    List<ProductSize> findAllByProductId(Long productId);
}
