package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {
    @Override
    public Product convert(ProductForm source) {
        return new Product()
                .setDescription(source.getDescription())
                .setCategoryId(source.getCategoryId())
                .setIsNew(source.getIsNew())
                .setName(source.getName())
                .setPageName(source.getPageName())
                .setPrice(source.getPrice());
    }
}
