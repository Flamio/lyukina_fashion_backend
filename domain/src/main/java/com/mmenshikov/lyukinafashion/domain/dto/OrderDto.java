package com.mmenshikov.lyukinafashion.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDto {
    private Long id;

    private String phone;

    private String address;
}
