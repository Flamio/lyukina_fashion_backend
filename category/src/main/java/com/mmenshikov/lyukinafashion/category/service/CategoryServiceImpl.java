package com.mmenshikov.lyukinafashion.category.service;

import com.mmenshikov.lyukinafashion.category.repository.CategoryRepository;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.entity.Category;
import com.mmenshikov.lyukinafashion.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ConversionService conversionService;

    @Override
    public List<CategoryDto> getAll() {
        final List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> conversionService.convert(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long addCategory(final CategoryForm categoryForm) {
        final Category category = conversionService.convert(categoryForm, Category.class);
        final Category savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }
}
