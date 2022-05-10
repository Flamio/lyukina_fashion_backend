package com.mmenshikov.lyukinafashion.category.service;

import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.category.domain.entity.Category;
import com.mmenshikov.lyukinafashion.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConversionService conversionService;

    public List<CategoryDto> getAll() {
        final List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> conversionService.convert(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public Long addCategory(final CategoryForm categoryForm) {
        final Category category = conversionService.convert(categoryForm, Category.class);
        final Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }
}
