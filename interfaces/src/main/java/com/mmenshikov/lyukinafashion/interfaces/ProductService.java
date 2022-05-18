package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductShortDto;
import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import org.springframework.data.util.Pair;

import java.util.List;

public interface ProductService {
    Pair<List<ProductShortDto>, Boolean> getProductsPage(final Integer productsPage, final Long categoryId);
    List<ProductShortDto> getNewProducts();
    ProductDto get(Long id);
    List<ProductDto> getAll();
    List<SizeDto> getSizes(Long id);
    ProductDto getByPageName(final String pageName);
    List<ProductDto> getList(List<Long> ids);
    void addProduct(final ProductForm productForm);
}