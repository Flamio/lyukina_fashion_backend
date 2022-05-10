package com.mmenshikov.lyukinafashion.category.converter;

import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.category.domain.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryFormToCategory implements Converter<CategoryForm, Category> {
    @Override
    public Category convert(CategoryForm source) {
        return new Category()
                .setIsNew(source.getIsNew())
                .setName(source.getName());
    }
}
