package com.mmenshikov.lyukinafashion.frontapi.service;

import com.mmenshikov.lyukinafashion.category.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.category.repository.CategoryRepository;
import com.mmenshikov.lyukinafashion.category.service.CategoryService;
import com.mmenshikov.lyukinafashion.frontapi.domain.dto.MainPageDto;
import com.mmenshikov.lyukinafashion.product.config.ProductsConfig;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final ProductsConfig productsConfig;
    private final ProductRepository productRepository;
    private final ConversionService conversionService;
    private final CategoryService categoryService;

    public MainPageDto getMainPage(final Integer productsPage) {
        final PageRequest pageRequest = PageRequest.of(productsPage, productsConfig.getItemsOnPage());
        final Page<Product> productPage = productRepository.findAll(pageRequest);
        final List<Product> newProducts = productRepository.findProductsByIsNewTrue();

        final List<ProductShortDto> newProductsConverted = newProducts.stream()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList());

        final List<ProductShortDto> otherProducts = productPage.get()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList());

        final List<CategoryDto> categories = categoryService.getAll();

        return new MainPageDto()
                .setNewProducts(newProductsConverted)
                .setMore(productPage.hasNext())
                .setOther(otherProducts)
                .setCategories(categories);
    }
}
