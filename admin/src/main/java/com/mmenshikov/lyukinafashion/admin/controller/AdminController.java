package com.mmenshikov.lyukinafashion.admin.controller;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
import com.mmenshikov.lyukinafashion.admin.service.AdminService;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @GetMapping("products")
    public List<ProductDto> getAllProducts() {
        return adminService.getAllProducts();
    }

    @PostMapping("product-upload")
    public void uploadProduct(@RequestPart("big-pics") List<MultipartFile> bigPics,
                              @RequestPart("thumbs") List<MultipartFile> thumbs,
                              @RequestPart("main-pic") MultipartFile mainPic,
                              @RequestPart("cart-thumb") MultipartFile cartThumb,
                              @RequestPart("product-dto") ProductUploadDto productDto

    ) {
        adminService.uploadProduct(bigPics, thumbs, mainPic, cartThumb, productDto);
    }

    @PostMapping("add-category")
    public Long addCategory(@RequestBody CategoryForm categoryForm) {
        return adminService.addCategory(categoryForm);
    }
}
