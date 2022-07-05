package com.mmenshikov.lyukinafashion.interfaces;

import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void uploadImages(List<MultipartFile> images, Long productId, String newFolderName, ProductObjectPurpose purpose);
    void updateImages(List<MultipartFile> images, Long productId, ProductObjectPurpose purpose);
}
