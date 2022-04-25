package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.product.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.ProductSize;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductSizeToSizeDto implements Converter<ProductSize, SizeDto> {
    @Override
    public SizeDto convert(ProductSize source) {
        return new SizeDto()
                .setId(source.getSize().getId())
                .setName(source.getSize().getName());
    }
}
