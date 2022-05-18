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
                .setBigPictures(source.getBigPictures())
                .setDescription(source.getDescription())
                .setCategoryId(source.getCategoryId())
                .setMainPicture(source.getPicture())
                .setIsNew(source.getIsNew())
                .setName(source.getName())
                .setThumbs(source.getThumbs())
                .setPageName(source.getPageName())
                .setPrice(source.getPrice())
                .setCartThumb(source.getCartThumb());
    }
}
