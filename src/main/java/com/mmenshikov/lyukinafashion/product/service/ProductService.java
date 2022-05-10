package com.mmenshikov.lyukinafashion.product.service;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.product.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.product.domain.entity.Product;
import com.mmenshikov.lyukinafashion.product.domain.entity.ProductSize;
import com.mmenshikov.lyukinafashion.product.exception.NotFoundException;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import com.mmenshikov.lyukinafashion.product.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ConversionService conversionService;
    private final ProductSizeRepository productSizeRepository;

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

    public List<ProductDto> getList(List<Long> ids) {
        final List<Product> products = productRepository.findAllByIds(ids);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addProduct(final ProductForm productForm) {
        final Product product = conversionService.convert(productForm, Product.class);
        if (product == null) {
            log.error("convert product failed");
            return;
        }

        final Product savedProduct = productRepository.save(product);

        final List<ProductSize> productSizes = productForm.getSizeIds()
                .stream()
                .map(sizeId -> new ProductSize()
                        .setProductId(savedProduct.getId())
                        .setSizeId(sizeId))
                .collect(Collectors.toList());

        productSizeRepository.saveAll(productSizes);
    }
}
