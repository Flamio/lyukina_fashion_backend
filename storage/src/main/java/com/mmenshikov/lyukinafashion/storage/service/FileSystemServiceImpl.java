package com.mmenshikov.lyukinafashion.storage.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileSystemServiceImpl implements FileSystemService{

    @Override
    public void copy(InputStream in, Path target) throws IOException {
        Files.copy(in, target);
    }

    @Override
    public void delete(Path path) throws IOException {
        Files.delete(path);
    }

    @Override
    public void createDirectories(Path path) throws IOException {
        Files.createDirectories(path);
    }
}
