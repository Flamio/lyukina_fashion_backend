package com.mmenshikov.lyukinafashion.admin.controller;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUpdateDto;
import com.mmenshikov.lyukinafashion.admin.service.AdminService;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

  @PostMapping(value = "products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void uploadProduct(@RequestPart("big-pics") List<MultipartFile> bigPics,
      @RequestPart("thumbs") List<MultipartFile> thumbs,
      @RequestPart("main-pic") MultipartFile mainPic,
      @RequestPart("cart-thumb") MultipartFile cartThumb,
      @Schema(
          description = "ProductForm",
          implementation = ProductForm.class)
      @RequestPart("product-dto") String productDto

  ) {
    adminService.uploadProduct(bigPics, thumbs, mainPic, cartThumb, productDto);
  }

  @PutMapping(value = "products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public void updateProduct(
      @RequestPart(name = "big-pics", required = false) List<MultipartFile> bigPics,
      @RequestPart(name = "thumbs", required = false) List<MultipartFile> thumbs,
      @RequestPart(name = "main-pic", required = false) MultipartFile mainPic,
      @RequestPart(name = "cart-thumb", required = false) MultipartFile cartThumb,
      @RequestPart(name = "product-dto", required = true) @Schema(
          description = "ProductUpdateDto",
          implementation = ProductUpdateDto.class)
          String productDto

  ) {
    adminService.updateProduct(bigPics, thumbs, mainPic, cartThumb, productDto);
  }

  @PostMapping("category")
  public Long addCategory(@RequestBody CategoryForm categoryForm) {
    return adminService.addCategory(categoryForm);
  }

  @GetMapping("category")
  public List<CategoryDto> getAllCategories() {
    return adminService.getAllCategories();
  }
}
