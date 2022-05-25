package com.mmenshikov.lyukinafashion.order.service;

import com.mmenshikov.lyukinafashion.domain.dto.OrderFormDto;
import com.mmenshikov.lyukinafashion.domain.entity.Order;
import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import com.mmenshikov.lyukinafashion.interfaces.OrderService;
import com.mmenshikov.lyukinafashion.order.repository.OrderRepository;
import com.mmenshikov.lyukinafashion.order.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final ConversionService conversionService;
    private final OrderRepository orderRepository;
    private final ProductOrderRepository productOrderRepository;
    private final Validator validator;

    @Override
    @Transactional
    public void createOrder(OrderFormDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        var order = conversionService.convert(dto, Order.class);
        if (order == null) {
            log.error("Ошибка конвертации orderForm");
            throw new RuntimeException("cannot convert dto to Order");
        }



        final Order savedOrder = orderRepository.save(order);

        var productOrder = new ProductOrder()
                .setOrder(savedOrder)
                .setProductId(dto.getProductId());

        productOrderRepository.save(productOrder);
    }
}