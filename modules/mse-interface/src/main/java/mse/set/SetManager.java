package mse.set;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SetManager {

    private final Path JMSE_SET_DIRECTORY = Path.of("jmse_sets");


    public static void main(String... args) throws IOException {
        SetManager setManager = new SetManager();
//        setManager.importSet("TestImport", Path.of("mse_sets/champions.mse-set").toFile());
        List<String> lines = setManager.load("TestImport");

        Set set = SetManager.read(lines);
        System.out.println(set);
    }

    /*============================== IMPORT ==============================*/
    public void importSet(String setName, File mseSet) throws IOException {
        File jmseDir = JMSE_SET_DIRECTORY.resolve(setName).toFile();

        //TODO: Implement check before overwriting existing data.
        recursiveDelete(jmseDir);
        if (!jmseDir.mkdirs()) {
            throw new IllegalStateException("Couldn't create directory: " + jmseDir);
        }

        //Copy the MSE set into the JMSE directory.
        Path jmseSet = Files.copy(mseSet.toPath(), jmseDir.toPath().resolve(mseSet.getName()));

        //Unzip the set.
        unzip(jmseSet.toFile());
    }

    public void unzip(File source) throws IOException {
        File dest = Path.of(source.getPath().split("\\.")[0]).toFile();
        byte[] buffer = new byte[1024];
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(source));) {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                File newFile = newZipEntryFile(dest, zipEntry);
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

    public File newZipEntryFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }

    private boolean recursiveDelete(File file) throws IOException {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (!recursiveDelete(child)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /*============================== LOAD ==============================*/

    public List<String> load(String setName) throws IOException {
        Path setDir = JMSE_SET_DIRECTORY.resolve(setName);
        Path setPath = getSetFile(setDir);
        if (setPath == null) {
            throw new IOException("Failed to find set file under: " + setDir);
        }
        return Files.readAllLines(setPath);
    }

    public Path getSetFile(Path setDir) {
        if (setDir.getFileName().toString().equals("set")) {
            return setDir;
        }
        File[] files = setDir.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                Path path = getSetFile(file.toPath());
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

    public static class Node<K, V> {
        protected final Map<K, V> fields = new LinkedHashMap<>();

        @Override
        public String toString() {
            List<String> list = new ArrayList<>();
            fields.forEach((k, v) -> {
                if (!(v instanceof String)) {
                    list.add("%s:%s".formatted(k, "\n" + v));
                } else {
                    list.add("%s:%s".formatted(k, v));
                }
            });
            return String.join("\n", list);
        }
    }

    public static class Set extends Node<String, Object> {
        protected List<Card> cards = new ArrayList<>();

        @Override
        public String toString() {
            return super.toString() + "\n" + String.join("\n", cards.stream().map(Card::toString).toList());
        }
    }

    public static class Card extends Node<String, String> {
        @Override
        public String toString() {
            return "card:\n" + super.toString();
        }
    }

    public static Set read(List<String> lines) {
        ListIterator<String> iterator = lines.listIterator();
        Set set = new Set();

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (!line.contains(":")) {
                //This is only true at the top level
                throw new RuntimeException("Line must contain colon ':' deliminator to split key and value. '%s'".formatted(line));
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index);

            if ("card".equals(key)) {
                Card value = readCard(iterator, 0);
                set.cards.add(value);
            } else if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1);
                set.fields.put(key, value);
            } else {
                //Object field.
                Object value = readObject(iterator, 0);
                set.fields.put(key, value);
            }
        }

        return set;
    }

    public static Object readObject(ListIterator<String> iterator, int parentDepth) {
        Node<String, Object> node = new Node<>();
        Object value = "";
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                return node.fields.isEmpty() ? "EMPTY" : node;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index);
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                value = line.substring(index + 1);
            } else {
                //Object field. Recurse.
                value = readObject(iterator, parentDepth + 1);
            }
            node.fields.put(key, value);
        }

        return node;
    }

    public static Card readCard(ListIterator<String> iterator, int parentDepth) {
        Card card = new Card();
        String value = "";
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index);
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                value = line.substring(index + 1);
            } else {
                //Long form field. Value is split across multiple lines.
                value = readLongField(iterator, parentDepth + 1);
            }
            card.fields.put(key, value);
        }

        return card;
    }

    public static String readLongField(ListIterator<String> iterator, int parentDepth) {
        List<String> lines = new ArrayList<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            lines.add(line);
        }

        return String.join("\n", lines);
    }


    private static int getDepth(String string) {
        int d = 0;
        while (string.startsWith("\t")) {
            d++;
            string = string.substring(1);
        }
        return d;
    }
}

