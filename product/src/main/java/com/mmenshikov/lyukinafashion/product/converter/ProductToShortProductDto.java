package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.domain.entity.Product;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.entity.StorageObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class ProductToShortProductDto implements Converter<Product, ProductShortDto> {

    @Override
    public ProductShortDto convert(Product source) {
        var mainPictures = source.getObjects()
                .stream()
                .filter(productObject -> productObject.getPurpose().equals(ProductObjectPurpose.MAIN_PICTURE))
                .map(StorageObject::getApiPath)
                .collect(Collectors.toList());
        return new ProductShortDto()
                .setId(source.getId())
                .setName(source.getName())
                .setPicture(CollectionUtils.isEmpty(mainPictures) ? null : mainPictures.get(0))
                .setPrice(source.getPrice())
                .setIsNew(source.getIsNew())
                .setPageName(source.getPageName());
    }
}
