package com.mmenshikov.lyukinafashion.product.controller;

import com.mmenshikov.lyukinafashion.product.domain.dto.ProductListDto;
import com.mmenshikov.lyukinafashion.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ProductListDto getAll(@RequestParam Integer page) {
        return productService.getAll(page);
    }

}
