package com.mmenshikov.lyukinafashion.category.converter;

import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.category.domain.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryDtoConverter implements Converter<Category, CategoryDto> {

    @Override
    public CategoryDto convert(Category source) {
        return new CategoryDto()
                .setId(source.getId())
                .setIsNew(source.getIsNew())
                .setName(source.getName());
    }
}
