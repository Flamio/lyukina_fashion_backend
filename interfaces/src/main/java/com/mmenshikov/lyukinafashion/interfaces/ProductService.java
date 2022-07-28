package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.entity.ProductSize;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ProductService {
    Pair<List<ProductShortDto>, Boolean> getProductsPage(final Integer productsPage, final Long categoryId);
    List<ProductShortDto> getNewProducts();
    ProductDto get(Long id);
    List<ProductDto> getAll();
    List<SizeDto> getSizes(Long id);
    ProductSize getProductSize(Long productId, Long sizeId);
    ProductDto getByPageName(final String pageName);
    List<ProductDto> getList(List<Long> ids);
    Long saveProduct(final ProductForm productForm, Long id);
}
