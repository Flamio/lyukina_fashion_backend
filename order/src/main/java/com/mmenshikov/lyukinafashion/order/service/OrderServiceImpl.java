package com.mmenshikov.lyukinafashion.order.service;

import com.mmenshikov.lyukinafashion.domain.dto.OrderFormDto;
import com.mmenshikov.lyukinafashion.domain.entity.OrderInfo;
import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import com.mmenshikov.lyukinafashion.interfaces.OrderService;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import com.mmenshikov.lyukinafashion.order.event.OrderSavedEvent;
import com.mmenshikov.lyukinafashion.order.repository.OrderInfoRepository;
import com.mmenshikov.lyukinafashion.order.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderInfoRepository orderInfoRepository;
    private final ProductOrderRepository productOrderRepository;
    private final Validator validator;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductService productService;

    @Override
    @Transactional
    public void createOrder(OrderFormDto dto) {
        var violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        var orderInfo = new OrderInfo()
                .setAddress(dto.getInfo().getAddress())
                .setPhone(dto.getInfo().getPhone())
                .setEmail(dto.getInfo().getEmail())
                .setContactName(dto.getInfo().getContactName())
                .setContactType(OrderInfo.ContactType.valueOf(dto.getInfo().getContactType()));

        var productOrders = dto.getProducts().stream().map(product -> {
            try {
                var productSize = productService.getProductSize(product.getProductId(), product.getSizeId());
                return new ProductOrder()
                        .setProductSizeId(productSize.getId())
                        .setProductSize(productSize)
                        .setQuantity(product.getQuantity())
                        .setOrderInfo(orderInfo);
            } catch (Exception exception) {
                log.error("Ошибка получения product size", exception);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        if (productOrders.isEmpty())
            return;

        orderInfoRepository.save(orderInfo);
        var savedProductOrders = productOrderRepository.saveAll(productOrders);

        applicationEventPublisher.publishEvent(new OrderSavedEvent(this, savedProductOrders));
    }


}
