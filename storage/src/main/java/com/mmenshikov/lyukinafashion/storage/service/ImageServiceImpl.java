package com.mmenshikov.lyukinafashion.storage.service;

import com.mmenshikov.lyukinafashion.domain.entity.ProductObject;
import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.entity.StorageObject;
import com.mmenshikov.lyukinafashion.interfaces.ImageService;
import com.mmenshikov.lyukinafashion.storage.config.StoreConfiguration;
import com.mmenshikov.lyukinafashion.storage.exception.ImageNotFoundException;
import com.mmenshikov.lyukinafashion.storage.exception.ImageUploadException;
import com.mmenshikov.lyukinafashion.storage.repository.ProductObjectRepository;
import com.mmenshikov.lyukinafashion.storage.repository.StorageObjectRepository;
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

    private final static String API_PATH = "/api/img?path=/";

    private final StoreConfiguration storeConfiguration;
    private final StorageObjectRepository storageObjectRepository;
    private final ProductObjectRepository productObjectRepository;

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
    public void uploadImages(final List<MultipartFile> images,
                             final Path path,
                             final Long productId,
                             final ProductObjectPurpose purpose) {

        final Path storePath = Path.of(storeConfiguration.getPath(), path.toString());
        createPath(storePath);
        images.forEach(file -> saveFile(file, storePath, path, productId, purpose));
    }

    private void saveFile(final MultipartFile file,
                            final Path storePath,
                            final Path path,
                            final Long productId,
                            final ProductObjectPurpose purpose) {

        final String imageName = UUID.randomUUID().toString();
        final Path imagePath = storePath.resolve(imageName);
        try {
            Files.copy(file.getInputStream(), imagePath);
        } catch (IOException e) {
            throwUploadException(e);
        }

        var imageApiPath = API_PATH + Path.of(path.toString(), imageName);

        var storageObject = new StorageObject()
                .setPath(imagePath.toString())
                .setApiPath(imageApiPath.replace('\\', '/'));

        final StorageObject savedStorageObject = storageObjectRepository.save(storageObject);

        var productObject = new ProductObject()
                .setProductId(productId)
                .setStorageObjectId(savedStorageObject.getId())
                .setPurpose(purpose);

        productObjectRepository.save(productObject);
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
