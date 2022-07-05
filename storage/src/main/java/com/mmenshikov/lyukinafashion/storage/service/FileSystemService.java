package com.mmenshikov.lyukinafashion.storage.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public interface FileSystemService {
    void copy(InputStream in, Path target) throws IOException;
    void delete(Path path) throws IOException;
    void createDirectories(Path path) throws IOException;
}
