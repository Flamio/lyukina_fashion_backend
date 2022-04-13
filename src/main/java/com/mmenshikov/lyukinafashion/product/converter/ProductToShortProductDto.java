package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToShortProductDto implements Converter<Product, ProductShortDto> {

    @Override
    public ProductShortDto convert(Product source) {
        return new ProductShortDto()
                .setId(source.getId())
                .setName(source.getName())
                .setPicture(source.getMainPicture())
                .setPrice(source.getPrice())
                .setIsNew(source.getIsNew());
    }
}
