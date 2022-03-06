package mse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    private ZipUtil() {
    }
    public static void unzip(Path source, Path destination) throws IOException {
        unzip(source.toFile(), destination.toFile());
    }

    public static void unzip(File source, File destination) throws IOException {
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source));) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = new File(destination, zipEntry.getName());
                String destDirPath = destination.getCanonicalPath();
                String destFilePath = newFile.getCanonicalPath();

                if (!destFilePath.startsWith(destDirPath + File.separator)) {
                    throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
                }

                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
        }
    }

    public static void zip(Path source, Path destination) throws IOException {
        zip(source.toFile(), destination.toFile());
    }

    public static void zip(File source, File destination) throws IOException {
        File[] files = Objects.requireNonNull(source.listFiles());
        FileOutputStream fos = new FileOutputStream(destination);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (File srcFile : files) {
            FileInputStream fis = new FileInputStream(srcFile);
            ZipEntry zipEntry = new ZipEntry(srcFile.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        }
        zipOut.close();
        fos.close();
    }
}
