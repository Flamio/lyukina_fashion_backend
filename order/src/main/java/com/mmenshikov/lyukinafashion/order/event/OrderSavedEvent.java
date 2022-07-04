package com.mmenshikov.lyukinafashion.order.event;

import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;


public class OrderSavedEvent extends ApplicationEvent {
    public OrderSavedEvent(Object source, List<ProductOrder> orders) {
        super(source);
        productOrders = orders;
    }

    @Getter
    private final List<ProductOrder> productOrders;
}
