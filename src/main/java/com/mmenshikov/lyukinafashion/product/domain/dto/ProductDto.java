package com.mmenshikov.lyukinafashion.product.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductDto {

    private Long id;
    private String name;
    private String picture;
    private BigDecimal price;

    @JsonProperty("new")
    private Boolean isNew;

    private String size;

    @JsonProperty("available_sizes")
    private String availableSizes;

    @JsonProperty("big_pics")
    private String bigPictures;

    private String thumbs;
}
