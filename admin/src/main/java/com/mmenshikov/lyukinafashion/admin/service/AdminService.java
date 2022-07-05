package com.mmenshikov.lyukinafashion.admin.service;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryDto;
import com.mmenshikov.lyukinafashion.domain.dto.CategoryForm;
import com.mmenshikov.lyukinafashion.domain.dto.ProductDto;
import com.mmenshikov.lyukinafashion.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.interfaces.CategoryService;
import com.mmenshikov.lyukinafashion.interfaces.ImageService;
import com.mmenshikov.lyukinafashion.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AdminService {

    private final ImageService imageService;
    private final ProductService productService;
    private final ConversionService conversionService;
    private final CategoryService categoryService;

    @Transactional
    public void uploadProduct(final List<MultipartFile> bigPics,
                              final List<MultipartFile> thumbs,
                              final MultipartFile mainPic,
                              final MultipartFile cartThumb,
                              final ProductUploadDto productDto) {


        final ProductForm productForm = conversionService.convert(productDto, ProductForm.class);
        if (productForm == null) {
            log.error("failed to convert product upload dto to product form");
            return;
        }

        var productId = productService.saveProduct(productForm);


        imageService.uploadImages(List.of(mainPic), productId, ProductObjectPurpose.MAIN_PICTURE);
        imageService.uploadImages(List.of(cartThumb), productId, ProductObjectPurpose.CART_THUMB);
        imageService.uploadImages(thumbs, productId, ProductObjectPurpose.THUMB);
        imageService.uploadImages(bigPics, productId, ProductObjectPurpose.BIG_PICTURE);
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
                              final ProductUploadDto productDto) {
        var existingProduct = productService.get(id);
        if (existingProduct == null) {
            var message = "product with id " + id + "not found";
            log.error(message);
            throw new RuntimeException(message);
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
                               final ProductUploadDto productUploadDto) {
        var productForm = conversionService.convert(existingProduct, ProductForm.class);
        if (productForm == null) {
            var message = "failed to convert product entity to product form";
            log.error(message);
            throw new RuntimeException(message);
        }
        if (productUploadDto.getCategoryId() != null)
            productForm.setCategoryId(productUploadDto.getCategoryId());

        if (productUploadDto.getPrice() != null)
            productForm.setPrice(productUploadDto.getPrice());

        if (productUploadDto.getIsNew() != null)
            productForm.setIsNew(productUploadDto.getIsNew());

        if (productUploadDto.getPageName() != null)
            productForm.setPageName(productUploadDto.getPageName());

        if (productUploadDto.getDescription() != null)
            productForm.setDescription(productUploadDto.getDescription());

        if (productUploadDto.getName() != null)
            productForm.setName(productUploadDto.getName());

        if (!CollectionUtils.isEmpty(productUploadDto.getSizeIds())) {
            productForm.setSizeIds(productUploadDto.getSizeIds());
        }

        productService.saveProduct(productForm);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }
}
