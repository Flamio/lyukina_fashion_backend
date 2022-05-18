package com.mmenshikov.lyukinafashion.storage.service;

import com.mmenshikov.lyukinafashion.interfaces.ImageService;
import com.mmenshikov.lyukinafashion.storage.config.StoreConfiguration;
import com.mmenshikov.lyukinafashion.storage.exception.ImageNotFoundException;
import com.mmenshikov.lyukinafashion.storage.exception.ImageUploadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final StoreConfiguration storeConfiguration;

    public Resource get(final String path) {
        final Path fullPath = Path.of(storeConfiguration.getPath(), path);

        log.info("downloading image {}", fullPath);

        try {
            return new InputStreamResource(new FileInputStream(fullPath.toString()));
        } catch (FileNotFoundException e) {
            log.error("{} not found", fullPath);
            throw new ImageNotFoundException(path);
        }
    }

    @Override
    public List<String> uploadImages(List<MultipartFile> images, Path path) {
        final Path storePath = Path.of(storeConfiguration.getPath(), path.toString());
        createPath(storePath);
        return images.stream()
                .map(multipartFile -> {
                    final String imageName = UUID.randomUUID().toString();
                    final Path imagePath = storePath.resolve(imageName);
                    try {
                        Files.copy(multipartFile.getInputStream(), imagePath);
                    } catch (IOException e) {
                        throwUploadException(e);
                    }
                    return API_PATH + Path.of(path.toString(), imageName);
                }).collect(Collectors.toList());
    }

    private void createPath(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throwUploadException(e);
        }
    }

    private void throwUploadException(Throwable throwable) {
        final ImageUploadException imageUploadException = new ImageUploadException(throwable);
        log.error("failed to upload image", imageUploadException);
        throw imageUploadException;
    }
}
