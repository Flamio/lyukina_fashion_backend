package com.mmenshikov.lyukinafashion.product.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductForm {
    private String name;
    private String picture;
    private BigDecimal price;

    private Boolean isNew;

    private List<Long> sizeIds;

    private Long categoryId;

    private String bigPictures;

    private String thumbs;

    private String cartThumb;

    private String description;
    private String pageName;
}
