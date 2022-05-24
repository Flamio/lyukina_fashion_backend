package com.mmenshikov.lyukinafashion.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductForm {
    private Long id;
    private String name;
    private BigDecimal price;

    private Boolean isNew;

    private List<Long> sizeIds;

    private Long categoryId;

    private String description;
    private String pageName;
}
