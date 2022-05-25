package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.dto.OrderFormDto;

public interface OrderService {
    void createOrder(OrderFormDto dto);
}
