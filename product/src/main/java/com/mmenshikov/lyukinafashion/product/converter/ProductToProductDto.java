package com.mmenshikov.lyukinafashion.product.converter;

import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.entity.Product;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.entity.StorageObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProductToProductDto implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product source) {
        return new ProductDto()
                .setId(source.getId())
                .setName(source.getName())
                .setPrice(source.getPrice())
                .setPicture(getObject(source.getObjects(), ProductObjectPurpose.MAIN_PICTURE))
                .setIsNew(source.getIsNew())
                .setBigPictures(getArrayOfObjects(source.getObjects(), ProductObjectPurpose.BIG_PICTURE))
                .setThumbs(getArrayOfObjects(source.getObjects(), ProductObjectPurpose.THUMB))
                .setDescription(source.getDescription())
                .setPageName(source.getPageName())
                .setCartThumb(getObject(source.getObjects(), ProductObjectPurpose.CART_THUMB));
    }

    private List<String> getArrayOfObjects(List<StorageObject> objects, ProductObjectPurpose purpose) {
        return objects
                .stream()
                .filter(productObject -> productObject.getPurpose().equals(purpose))
                .map(StorageObject::getApiPath)
                .collect(Collectors.toList());
    }

    private String getObject(List<StorageObject> objects, ProductObjectPurpose purpose) {
        var listObjects = objects
                .stream()
                .filter(productObject -> productObject.getPurpose().equals(purpose))
                .map(StorageObject::getApiPath)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(listObjects)) {
            return null;
        }

        return listObjects.get(0);
    }
}
