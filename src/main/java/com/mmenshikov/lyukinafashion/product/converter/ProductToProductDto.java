package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product source) {
        return new ProductDto()
                .setId(source.getId())
                .setName(source.getName())
                .setPicture(source.getMainPicture())
                .setPrice(source.getPrice())
                .setIsNew(source.getIsNew())
                .setBigPictures(source.getBigPictures())
                .setThumbs(source.getThumbs())
                .setSizes(source
                        .getProductSizes()
                        .stream()
                        .map(productSize -> productSize.getSize().getName())
                        .collect(Collectors.toList()));
    }
}
