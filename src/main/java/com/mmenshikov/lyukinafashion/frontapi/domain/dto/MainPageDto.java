package com.mmenshikov.lyukinafashion.frontapi.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductShortDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MainPageDto {

    @JsonProperty("new")
    private List<ProductShortDto> newProducts;

    private List<ProductShortDto> other;

    private Boolean more;

    private List<CategoryDto> categories;
}
