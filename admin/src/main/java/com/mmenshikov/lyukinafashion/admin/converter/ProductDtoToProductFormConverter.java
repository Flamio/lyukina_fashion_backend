package com.mmenshikov.lyukinafashion.admin.converter;

import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductDtoToProductFormConverter implements Converter<ProductDto, ProductForm> {
    @Override
    public ProductForm convert(ProductDto source) {
        return new ProductForm()
                .setId(source.getId())
                .setDescription(source.getDescription())
                .setPrice(source.getPrice())
                .setName(source.getName())
                .setPageName(source.getPageName())
                .setIsNew(source.getIsNew())
                .setCategoryId(source.getCategory().getId())
                .setSizeIds(source.getSizes()
                        .stream()
                        .map(SizeDto::getId)
                        .collect(Collectors.toList()));
    }
}
