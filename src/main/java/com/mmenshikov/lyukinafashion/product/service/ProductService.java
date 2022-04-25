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
        final ProductDto productDto = conversionService.convert(product, ProductDto.class);
        final List<SizeDto> sizeDtoList = product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
        productDto.setSizes(sizeDtoList);
        return productDto;
    }

    public List<SizeDto> getSizes(Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
    }
}
