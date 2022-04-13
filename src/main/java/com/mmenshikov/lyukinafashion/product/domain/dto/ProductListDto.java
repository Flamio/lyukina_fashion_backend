package com.mmenshikov.lyukinafashion.product.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductListDto {

    @JsonProperty("new")
    private List<ProductShortDto> newProducts;

    private List<ProductShortDto> other;

    private Boolean more;
}
