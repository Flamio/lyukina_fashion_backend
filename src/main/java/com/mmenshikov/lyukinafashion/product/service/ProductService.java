package com.mmenshikov.lyukinafashion.product.service;

import com.mmenshikov.lyukinafashion.product.config.ProductsConfig;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductListDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsConfig productsConfig;
    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    public ProductListDto getAll(@PathVariable Integer page) {
        final PageRequest pageRequest = PageRequest.of(page, productsConfig.getItemsOnPage());
        final Page<Product> productPage = productRepository.findAll(pageRequest);
        final List<Product> newProducts = productRepository.findProductsByIsNewTrue();

        final List<ProductShortDto> newProductsConverted = newProducts.stream()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList());

        final List<ProductShortDto> otherProducts = productPage.get()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList());

        return new ProductListDto()
                .setNewProducts(newProductsConverted)
                .setMore(productPage.hasNext())
                .setOther(otherProducts);

    }
}
