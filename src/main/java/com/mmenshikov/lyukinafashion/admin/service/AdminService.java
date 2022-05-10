package com.mmenshikov.lyukinafashion.admin.service;

import com.mmenshikov.lyukinafashion.admin.dto.ProductUploadDto;
import com.mmenshikov.lyukinafashion.product.domain.dto.ProductForm;
import com.mmenshikov.lyukinafashion.product.service.ProductService;
import com.mmenshikov.lyukinafashion.storage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    public void uploadProduct(final List<MultipartFile> bigPics,
                              final List<MultipartFile> thumbs,
                              final MultipartFile mainPic,
                              final ProductUploadDto productDto) {

        final String newProductDirectoryName = UUID.randomUUID().toString();

        final String mainPicPath = imageService.uploadImages(List.of(mainPic),
                Path.of(newProductDirectoryName)).get(0);
        final List<String> thumbsPaths = imageService.uploadImages(thumbs,
                Path.of(newProductDirectoryName, ImageService.THUMBS_DIRECTORY_NAME));
        final List<String> bigPicsPaths = imageService.uploadImages(bigPics,
                Path.of(newProductDirectoryName, ImageService.BIG_PICS_DIRECTORY_NAME));



        final ProductForm productForm = conversionService.convert(productDto, ProductForm.class);
        if (productForm == null) {
            log.error("failed to convert product upload dto to product form");
            return;
        }
        productForm.setPicture(mainPicPath.replace('\\', '/'));
        productForm.setThumbs(String.join(",", thumbsPaths).replace('\\', '/'));
        productForm.setBigPictures(String.join(",", bigPicsPaths).replace('\\', '/'));

        productService.addProduct(productForm);
    }
}
