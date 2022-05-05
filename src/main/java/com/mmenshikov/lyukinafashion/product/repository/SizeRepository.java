package com.mmenshikov.lyukinafashion.product.repository;

import com.mmenshikov.lyukinafashion.product.domain.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    List<Size> findAllById(Iterable<Long> ids);
}
