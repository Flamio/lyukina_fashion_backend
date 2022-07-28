package com.mmenshikov.lyukinafashion.admin.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductUpdateDto {
    @NotNull
    private Long id;

    private String name;

    private BigDecimal price;

    private Boolean isNew;

    private String pageName;

    private Long categoryId;

    private String description;

    private List<Long> sizeIds;
}
