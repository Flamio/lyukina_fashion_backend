package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    Long addCategory(final CategoryForm categoryForm);
}
