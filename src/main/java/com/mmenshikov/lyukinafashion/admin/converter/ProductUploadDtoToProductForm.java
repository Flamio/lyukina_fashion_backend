package com.mmenshikov.lyukinafashion.admin.converter;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductForm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductUploadDtoToProductForm implements Converter<ProductUploadDto, ProductForm> {

    @Override
    public ProductForm convert(ProductUploadDto source) {
        return new ProductForm()
                .setName(source.getName())
                .setCategoryId(source.getCategoryId())
                .setDescription(source.getDescription())
                .setIsNew(source.getIsNew())
                .setPrice(source.getPrice())
                .setSizeIds(source.getSizeIds())
                .setPageName(source.getPageName());
    }
}
