package com.mmenshikov.lyukinafashion.product.controller;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    public ProductDto get(@PathVariable Long id) {
        return productService.get(id);
    }

    @GetMapping("list/{ids}")
    public List<ProductDto> getList(@PathVariable List<Long> ids) {return productService.getList(ids); }

    @GetMapping("by-page-name/{pageName}")
    public ProductDto getByPageName(@PathVariable String pageName) {
        return productService.getByPageName(pageName);
    }

    @GetMapping("{id}/sizes")
    public List<SizeDto> getSizes(@PathVariable Long id) {
        return productService.getSizes(id);
    }
}
