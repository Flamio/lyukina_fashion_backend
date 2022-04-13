package com.mmenshikov.lyukinafashion.storage.service;

import com.mmenshikov.lyukinafashion.storage.config.StoreConfiguration;
import com.mmenshikov.lyukinafashion.storage.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageService {

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
}
