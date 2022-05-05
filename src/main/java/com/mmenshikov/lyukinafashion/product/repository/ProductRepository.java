package com.mmenshikov.lyukinafashion.product.repository;

import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByIsNewTrue();
    Optional<Product> findByPageName(String pageName);
    @Query("select p from Product p where id  in :ids")
    List<Product> findAllByIds(List<Long> ids);
}
