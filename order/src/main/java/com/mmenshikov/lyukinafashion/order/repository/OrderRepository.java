package com.mmenshikov.lyukinafashion.order.repository;

import com.mmenshikov.lyukinafashion.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
