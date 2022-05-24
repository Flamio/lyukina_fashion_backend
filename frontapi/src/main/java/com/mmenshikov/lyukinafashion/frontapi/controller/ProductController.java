package com.mmenshikov.lyukinafashion.frontapi.controller;

import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.SizeDto;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("front/products")
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
