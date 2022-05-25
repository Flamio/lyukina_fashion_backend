package com.mmenshikov.lyukinafashion.order.converter;

import com.mmenshikov.lyukinafashion.domain.dto.OrderFormDto;
import com.mmenshikov.lyukinafashion.domain.entity.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderFormDtoToOrderConverter implements Converter<OrderFormDto, Order> {
    
    @Override
    public Order convert(OrderFormDto source) {
        return new Order()
                .setAddress(source.getAddress())
                .setPhone(source.getPhone());
    }
}
