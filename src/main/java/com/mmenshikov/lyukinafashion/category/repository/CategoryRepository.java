package com.mmenshikov.lyukinafashion.category.repository;

import com.mmenshikov.lyukinafashion.category.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
