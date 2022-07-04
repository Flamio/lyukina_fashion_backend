package com.mmenshikov.lyukinafashion.order.repository;

import com.mmenshikov.lyukinafashion.domain.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}
