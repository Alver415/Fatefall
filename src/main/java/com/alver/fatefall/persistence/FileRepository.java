package com.alver.fatefall.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class FileRepository {

    protected final Path path;

    protected final FileNode<FileRepository> root;

    public FileRepository(Path path) throws IOException {
        this.path = path;
        this.root = new FileNode<>();
        this.root.name = "root";
        this.root.cachedObject = this;
        initialize();
    }

    private void initialize() throws IOException {
        File repo = new File(root.getName());
        List<File> list = Arrays.stream(Objects.requireNonNull(repo.listFiles())).toList();
        for (File item : list) {
            if (item.isDirectory()) {
                FileNode<?> child = read(item);
                root.children.add(child);
            }
            if (item.isFile()) {
                if (item.getName().equals(root.getName())) {
                    root.supplier = () -> deserialize(item);
                } else {
                    root.data.put(item.getName(), item);
                }
            }
        }
    }

    private <T> T deserialize(File item) {
        return null;
    }


    public <T> FileNode<T> read(File directory) {
        FileNode<T> node = new FileNode<>();
        for (File item : Arrays.stream(Objects.requireNonNull(directory.listFiles())).toList()) {
            if (item.isDirectory()) {
                FileNode<?> child = read(item);
                root.children.add(child);
            }
            if (item.isFile()) {
                if (item.getName().equals(root.getName())) {
                    root.supplier = () -> deserialize(item);
                } else {
                    root.data.put(item.getName(), item);
                }
            }
        }
        return node;
    }
    public <T> FileNode<T> read(Class<T> type, File file) {
        return null;
    }

    public <T> FileNode<T> write(T object, File file) {
        return null;
    }

    public FileNode<?> find(String path) {
        return null;
    }


}
