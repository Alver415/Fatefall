package com.alver.fatefall.api.server.repositories;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

@Repository
public class FileSystemRepository {
    private static final String RESOURCES_DIR = "database/images/";

    public String save(byte[] content, String name) throws IOException {
        Path newFile = Paths.get(RESOURCES_DIR + new Date().getTime() + "-" + name + ".png");
        Files.createDirectories(newFile.getParent());
        Files.write(newFile, content);
        return newFile.toAbsolutePath().toString();
    }

    public FileSystemResource findInFileSystem(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            //TODO: Add error handling.
            throw new RuntimeException(e);
        }
    }
}