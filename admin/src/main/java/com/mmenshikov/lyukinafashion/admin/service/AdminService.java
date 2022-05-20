package com.mmenshikov.lyukinafashion.admin.service;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
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
        productForm.setId(productId);

        final String newProductDirectoryName = UUID.randomUUID().toString();

        imageService.uploadImages(List.of(mainPic),
                Path.of(newProductDirectoryName), productId, ProductObjectPurpose.MAIN_PICTURE);
        imageService.uploadImages(List.of(cartThumb),
                Path.of(newProductDirectoryName), productId, ProductObjectPurpose.CART_THUMB);
        imageService.uploadImages(thumbs,
                Path.of(newProductDirectoryName), productId, ProductObjectPurpose.THUMB);
        imageService.uploadImages(bigPics,
                Path.of(newProductDirectoryName), productId, ProductObjectPurpose.BIG_PICTURE);


    }

    public Long addCategory(final CategoryForm categoryForm) {
        return categoryService.addCategory(categoryForm);
    }

    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }
}
