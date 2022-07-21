package com.mmenshikov.lyukinafashion.admin.service;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUpdateDto;
import com.mmenshikov.lyukinafashion.admin.mapper.ProductMapper;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.exception.BaseException;
import com.mmenshikov.lyukinafashion.interfaces.CategoryService;
import com.mmenshikov.lyukinafashion.interfaces.ImageService;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AdminService {

  private final ImageService imageService;
  private final ProductService productService;
  private final CategoryService categoryService;
  private final ProductMapper productMapper;

  @Transactional
  public void uploadProduct(final List<MultipartFile> bigPics,
      final List<MultipartFile> thumbs,
      final MultipartFile mainPic,
      final MultipartFile cartThumb,
      final ProductForm productForm) {

    var productId = productService.saveProduct(productForm);

    var newFolderName = UUID.randomUUID().toString();

    imageService.uploadImages(List.of(mainPic), productId, newFolderName,
        ProductObjectPurpose.MAIN_PICTURE);
    imageService.uploadImages(List.of(cartThumb), productId, newFolderName,
        ProductObjectPurpose.CART_THUMB);
    imageService.uploadImages(thumbs, productId, newFolderName, ProductObjectPurpose.THUMB);
    imageService.uploadImages(bigPics, productId, newFolderName, ProductObjectPurpose.BIG_PICTURE);
  }

  public Long addCategory(final CategoryForm categoryForm) {
    return categoryService.addCategory(categoryForm);
  }

  public List<ProductDto> getAllProducts() {
    return productService.getAll();
  }

  @Transactional
  public void updateProduct(final Long id,
      final List<MultipartFile> bigPics,
      final List<MultipartFile> thumbs,
      final MultipartFile mainPic,
      final MultipartFile cartThumb,
      final ProductUpdateDto productDto) {

    var existingProduct = productService.get(id);
    if (existingProduct == null) {
      var message = "product with id " + id + "not found";
      log.error(message);
      throw new BaseException(message);
    }

    if (productDto != null) {
      updateProduct(existingProduct, productDto);
    }

    if (mainPic != null) {
      imageService.updateImages(List.of(mainPic), id, ProductObjectPurpose.MAIN_PICTURE);
    }
    if (cartThumb != null) {
      imageService.updateImages(List.of(cartThumb), id, ProductObjectPurpose.CART_THUMB);
    }
    if (!CollectionUtils.isEmpty(thumbs)) {
      imageService.updateImages(thumbs, id, ProductObjectPurpose.THUMB);
    }

    if (!CollectionUtils.isEmpty(bigPics)) {
      imageService.updateImages(bigPics, id, ProductObjectPurpose.BIG_PICTURE);
    }
  }

  private void updateProduct(final ProductDto existingProduct,
      final ProductUpdateDto productUploadDto) {

    var productForm = productMapper.toProductForm(existingProduct);

    if (productForm == null) {
      var message = "failed to convert product entity to product form";
      log.error(message);
      throw new BaseException(message);
    }

    productMapper.update(productForm, productUploadDto);

    productService.saveProduct(productForm);
  }

  public List<CategoryDto> getAllCategories() {
    return categoryService.getAll();
  }
}
