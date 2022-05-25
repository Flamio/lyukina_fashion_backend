package com.mmenshikov.lyukinafashion.order.repository;

import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
