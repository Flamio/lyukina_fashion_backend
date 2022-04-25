package com.mmenshikov.lyukinafashion.product.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SizeDto {
    private Long id;
    private String name;
}
