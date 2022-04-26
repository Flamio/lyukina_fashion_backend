package com.mmenshikov.lyukinafashion.product.repository;

import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByIsNewTrue();
    Optional<Product> findByPageName(String pageName);
}
