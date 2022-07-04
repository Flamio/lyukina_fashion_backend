package com.mmenshikov.lyukinafashion.product.service;

import com.mmenshikov.lyukinafashion.domain.dto.*;
import com.mmenshikov.lyukinafashion.domain.entity.Product;
import com.mmenshikov.lyukinafashion.domain.entity.ProductSize;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import com.mmenshikov.lyukinafashion.product.config.ProductsConfig;
import com.mmenshikov.lyukinafashion.product.exception.NotFoundException;
import com.mmenshikov.lyukinafashion.product.repository.ProductRepository;
import com.mmenshikov.lyukinafashion.product.repository.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ConversionService conversionService;
    private final ProductSizeRepository productSizeRepository;
    private final ProductsConfig productsConfig;

    @Override
    public Pair<List<ProductShortDto>, Boolean> getProductsPage(final Integer productsPage, final Long categoryId) {
        final PageRequest pageRequest = PageRequest.of(productsPage, productsConfig.getItemsOnPage());
        final Page<Product> productPage = categoryId == null ?
                productRepository.findAll(pageRequest) :
                productRepository.findAllByCategoryId(pageRequest, categoryId);

        return Pair.of(productPage.get()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList()), productPage.hasNext());
    }


    @Override
    public List<ProductShortDto> getNewProducts() {
        final List<Product> newProducts = productRepository.findProductsByIsNewTrue();

        return newProducts.stream()
                .map(product -> conversionService.convert(product, ProductShortDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto get(Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return convertToDto(product);
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SizeDto> getSizes(Long id) {
        final Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        return product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductSize getProductSize(Long productId, Long sizeId) {
        var productSizeOptional = productSizeRepository.findByProductIdAndSizeId(productId, sizeId);
        return productSizeOptional.orElseThrow(() -> new NotFoundException(productId, sizeId));
    }

    @Override
    public ProductDto getByPageName(final String pageName) {
        final Product product = productRepository.findByPageName(pageName)
                .orElseThrow(() -> new NotFoundException(pageName));
        return convertToDto(product);
    }

    private ProductDto convertToDto(final Product product) {
        final CategoryDto categoryDto = conversionService.convert(product.getCategory(), CategoryDto.class);
        final ProductDto productDto = conversionService.convert(product, ProductDto.class);
        final List<SizeDto> sizeDtoList = product.getProductSizes()
                .stream()
                .map(productSize -> conversionService.convert(productSize, SizeDto.class))
                .collect(Collectors.toList());
        productDto.setSizes(sizeDtoList);
        productDto.setCategory(categoryDto);
        return productDto;
    }

    @Override
    public List<ProductDto> getList(List<Long> ids) {
        final List<Product> products = productRepository.findAllByIds(ids);
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long saveProduct(final ProductForm productForm) {
        final Product product = conversionService.convert(productForm, Product.class);
        if (product == null) {
            log.error("convert product failed");
            return null;
        }

        final Product savedProduct = productRepository.save(product);

        var productSizes = productSizeRepository.findAllByProductId(savedProduct.getId());
        if (needToDeleteProductSizes(productSizes, productForm.getSizeIds())) {
            productSizeRepository.deleteAll(productSizes);
        }

        final List<ProductSize> newProductSizes = productForm.getSizeIds()
                .stream()
                .map(sizeId -> new ProductSize()
                        .setProductId(savedProduct.getId())
                        .setSizeId(sizeId))
                .collect(Collectors.toList());

        productSizeRepository.saveAll(newProductSizes);
        return savedProduct.getId();
    }

    private boolean needToDeleteProductSizes(final List<ProductSize> productSizes,
                                             final List<Long> sizeIdsToAdd) {
        return !CollectionUtils.isEmpty(productSizes) && !CollectionUtils.isEmpty(sizeIdsToAdd);
    }
}
