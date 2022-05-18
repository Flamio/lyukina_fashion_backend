package com.mmenshikov.lyukinafashion.frontapi.service;

import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.frontapi.domain.dto.MainPageDto;
import com.mmenshikov.lyukinafashion.interfaces.CategoryService;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPageService {
    private final CategoryService categoryService;
    private final ProductService productService;

    public MainPageDto getMainPage(final Integer productsPage, final Long categoryId) {


        final List<CategoryDto> categories = categoryService.getAll();

        var productPage = productService.getProductsPage(productsPage, categoryId);

        return new MainPageDto()
                .setNewProducts(productService.getNewProducts())
                .setMore(productPage.getSecond())
                .setOther(productPage.getFirst())
                .setCategories(categories);
    }
}
