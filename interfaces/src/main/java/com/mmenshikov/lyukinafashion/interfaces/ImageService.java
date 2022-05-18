package com.mmenshikov.lyukinafashion.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface ImageService {
    String THUMBS_DIRECTORY_NAME = "thumbs";
    String BIG_PICS_DIRECTORY_NAME = "big-pics";
    String API_PATH = "/api/img?path=/";

    List<String> uploadImages(List<MultipartFile> images, Path path);
}
