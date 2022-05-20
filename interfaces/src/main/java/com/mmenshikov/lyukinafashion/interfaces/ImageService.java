package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ImageService {
    void uploadImages(List<MultipartFile> images, Path path, Long productId, ProductObjectPurpose purpose);
}
