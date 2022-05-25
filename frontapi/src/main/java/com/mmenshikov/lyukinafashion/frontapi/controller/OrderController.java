package com.mmenshikov.lyukinafashion.frontapi.controller;

import com.mmenshikov.lyukinafashion.domain.dto.OrderFormDto;
import com.mmenshikov.lyukinafashion.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("front/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public void createOrder(@RequestBody OrderFormDto orderFormDto) {
        orderService.createOrder(orderFormDto);
    }
}
