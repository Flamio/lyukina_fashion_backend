package com.mmenshikov.lyukinafashion.order.event;

import com.mmenshikov.lyukinafashion.domain.entity.ProductOrder;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


public class OrderSavedEvent extends ApplicationEvent {
    public OrderSavedEvent(Object source, ProductOrder order) {
        super(source);
        productOrder = order;
    }

    @Getter
    private final ProductOrder productOrder;
}
