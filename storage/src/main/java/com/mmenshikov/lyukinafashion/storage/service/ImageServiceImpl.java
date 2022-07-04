package com.mmenshikov.lyukinafashion.storage.service;

import com.mmenshikov.lyukinafashion.domain.entity.ProductObjectPurpose;
import com.mmenshikov.lyukinafashion.domain.entity.StorageObject;
import com.mmenshikov.lyukinafashion.interfaces.ImageService;
import com.mmenshikov.lyukinafashion.storage.config.StoreConfiguration;
import com.mmenshikov.lyukinafashion.storage.exception.ImageNotFoundException;
import com.mmenshikov.lyukinafashion.storage.exception.ImageUploadException;
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
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final static String API_PATH = "/api/img?path=/";

    private final StoreConfiguration storeConfiguration;
    private final StorageObjectRepository storageObjectRepository;

    public Resource get(final String path) {
        final Path fullPath = Path.of(storeConfiguration.getPath(), path);

        log.debug("downloading image {}", fullPath);

        try {
            return new InputStreamResource(new FileInputStream(fullPath.toString()));
        } catch (FileNotFoundException e) {
            log.error("{} not found", fullPath);
            throw new ImageNotFoundException(path);
        }
    }

    @Override
    public void uploadImages(final List<MultipartFile> images,
                             final Long productId,
                             final ProductObjectPurpose purpose) {

        var newFolderName = UUID.randomUUID().toString();
        final Path storePath = Path.of(storeConfiguration.getPath(), newFolderName);
        createPath(storePath);
        images.forEach(file -> saveFile(file, storePath, newFolderName, productId, purpose));
    }

    @Override
    public void updateImages(final List<MultipartFile> images,
                             final Long productId,
                             final ProductObjectPurpose purpose) {

        final List<StorageObject> objects = storageObjectRepository.findAllByProductId(productId);
        var oldPurposeObjects = objects.stream()
                .filter(storageObject -> storageObject.getPurpose().equals(purpose))
                .collect(Collectors.toList());
        var folder = Path.of(oldPurposeObjects.get(0).getPath()).getParent();
        deleteOldImages(oldPurposeObjects);
        images.forEach(file -> saveFile(file, folder, folder.getFileName().toString(), productId, purpose));
        storageObjectRepository.deleteAll(oldPurposeObjects);
    }

    private void deleteOldImages(List<StorageObject> objects) {
        objects.forEach(storageObject -> {
            try {
                Files.delete(Path.of(storageObject.getPath()));
            } catch (IOException e) {
                log.error("Failed to delete old image {}", storageObject.getPath(), e);
            }
        });
    }

    private void saveFile(final MultipartFile file,
                          final Path folderPath,
                          final String folderName,
                          final Long productId,
                          final ProductObjectPurpose purpose) {

        final String imageName = UUID.randomUUID().toString();
        final Path imagePath = folderPath.resolve(imageName);
        try {
            Files.copy(file.getInputStream(), imagePath);
        } catch (IOException e) {
            throwUploadException(e);
        }

        var imageApiPath = API_PATH + Path.of(folderName, imageName);

        var storageObject = new StorageObject()
                .setPath(imagePath.toString())
                .setApiPath(imageApiPath.replace('\\', '/'))
                .setProductId(productId)
                .setPurpose(purpose);

        storageObjectRepository.save(storageObject);
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
