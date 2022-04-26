package com.mmenshikov.lyukinafashion.product.service;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import com.mmenshikov.lyukinafashion.product.exception.NotFoundException;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    public ProductDto get(Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return convertToDto(product);
    }

    public List<SizeDto> getSizes(Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
    }

    public ProductDto getByPageName(final String pageName) {
        final Product product = productRepository.findByPageName(pageName)
                .orElseThrow(() -> new NotFoundException(pageName));
        return convertToDto(product);
    }

    private ProductDto convertToDto(final Product product) {
        final ProductDto productDto = conversionService.convert(product, ProductDto.class);
        final List<SizeDto> sizeDtoList = product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
        productDto.setSizes(sizeDtoList);
        return productDto;
    }
}
