package mse;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetManager {

    private static final Path JMSE_SET_DIRECTORY = Path.of("jmse_sets");
    private static final boolean overwriteExisting = true;

    private final String setName;
    private final String fileName;
    private Set set;

    private SetManager(String setName, String fileName) throws IOException {
        this.setName = setName;
        this.fileName = fileName;
        this.set = load();
    }

    public static SetManager get(String setName) throws IOException {
        Path basePath = JMSE_SET_DIRECTORY.resolve(setName);
        //TODO: Just guesses based on the first filename in the directory...
        String fileName = Objects.requireNonNull(basePath.toFile().list())[0].split("\\.")[0];
        return new SetManager(setName, fileName);
    }

    public static SetManager importMseSet(String setName, Path source) throws IOException {
        //Parse the file name of source file (everything before .mse-set)
        String sourceFileName = source.getFileName().toString();
        int index = sourceFileName.lastIndexOf(".mse-set");
        String fileName = sourceFileName.substring(0, index);

        //Create the directory to store everything in based on the given setName
        Path basePath = createDirectory(JMSE_SET_DIRECTORY.resolve(setName));

        //Import the .mse-set file to the new directory (simple copy)
        Path importPath = basePath.resolve(sourceFileName);
        Files.copy(source, importPath);

        //Unzip the contents of the newly imported .mse-set file.
        Path unzippedPath = basePath.resolve(fileName);
        ZipUtil.unzip(importPath, unzippedPath);

        SetManager setManager = new SetManager(setName, fileName);
        setManager.generateImages();

        return setManager;
    }

    public Set getSet() {
        return set;
    }
    public void setSet(Set set) {
        this.set = set;
    }

    public Path getDirectoryPath() {
        return JMSE_SET_DIRECTORY.resolve(setName);
    }

    public Path getUnzippedPath() {
        return getDirectoryPath().resolve(fileName);
    }

    public Path getSetPath() {
        return getUnzippedPath().resolve("set");
    }

    public Path getImportPath() {
        return getDirectoryPath().resolve(fileName + ".mse-set");
    }

    public Path getExportPath() {
        return getDirectoryPath().resolve(fileName + ".exported.mse-set");
    }

    public Path getImagesPath() {
        return getDirectoryPath().resolve("images");
    }

    public void exportSet() throws IOException {
        ZipUtil.zip(getUnzippedPath(), getExportPath());
    }

    public void generateImages() throws IOException {
        exportSet();
        File imagesDirectory = getImagesPath().toFile();
        FileUtils.deleteDirectory(imagesDirectory);
        if (!imagesDirectory.exists()) {
            if (!imagesDirectory.mkdirs()) {
                throw new IOException("Failed to create directory: " + imagesDirectory);
            }
        }

        try (MseCliProcess mse = new MseCliProcess()) {
            mse.load(getExportPath());
            int count = Integer.parseInt(mse.command("number_of_items(in: set.cards)").lines().get(0));
            for (int i = 0; i < count; i++) {
                String name = mse.command("set.cards[%s].name".formatted(i)).lines().get(0)
                        .replace("\"", "")
                        .replace(",", "")
                        .replace(" ", "_");
                mse.write_image_file("set.cards[%s]".formatted(i), getImagesPath().resolve(name + ".png"));
            }
            mse.quit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*============================== LOAD ==============================*/

    public Set load() throws IOException {
        List<String> lines = Files.readAllLines(getSetPath());
        ListIterator<String> iterator = lines.listIterator();
        set = new Set();

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (!line.contains(":")) {
                //This is only true at the top level
                throw new RuntimeException("Line must contain colon ':' deliminator to split key and value. '%s'".formatted(line));
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();

            if ("card".equals(key)) {
                Card value = readCard(iterator, 0);
                set.cards.add(value);
            } else if ("keyword".equals(key)) {
                Keyword value = readKeyword(iterator, 0);
                set.keywords.add(value);
            } else if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1).trim();
                set.fields.put(key, value);
            } else {
                //Object field.
                Object value = readObject(iterator, 0);
                set.fields.put(key, value);
            }
        }

        return set;
    }

    private Object readObject(ListIterator<String> iterator, int parentDepth) {
        Node<Object> node = new Node<>();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                return node.fields.isEmpty() ? "EMPTY" : node;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1).trim();
                node.fields.put(key, value);
            } else {
                //Object field. Recurse.
                Object value = readObject(iterator, parentDepth + 1);
                node.fields.put(key, value);
            }
        }

        return node;
    }

    private Card readCard(ListIterator<String> iterator, int parentDepth) {
        Card card = new Card();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1);
                card.fields.put(key, value.trim());
            } else {
                //Long form field. Value is split across multiple lines.
                String value = readBigField(iterator, parentDepth + 1).trim();
                card.fields.put(key, value);
            }
        }

        return card;
    }

    private Keyword readKeyword(ListIterator<String> iterator, int parentDepth) {
        Keyword keyword = new Keyword();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (getDepth(line) <= parentDepth) {
                iterator.previous();
                break;
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();
            if (line.length() > index + 1) {
                //Simple field. Value is on the same line as the key.
                String value = line.substring(index + 1);
                keyword.fields.put(key, value.trim());
            } else {
                //Long form field. Value is split across multiple lines.
                String value = readBigField(iterator, parentDepth + 1).trim();
                keyword.fields.put(key, value);
            }
        }

        return keyword;
    }

    private String readBigField(ListIterator<String> iterator, int parentDepth) {
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

    private int getDepth(String string) {
        int d = 0;
        while (string.startsWith("\t")) {
            d++;
            string = string.substring(1);
        }
        return d;
    }

    /*============================== Write ==============================*/

    public void save() throws IOException {
        Stream<String> fields = set.fields.entrySet().stream()
                .map(e -> writeField(0, e.getKey(), e.getValue()));

        Stream<String> cards = set.cards.stream()
                .map(this::writeCard);

        Stream<String> keywords = set.keywords.stream()
                .map(this::writeKeyword);

        String setString = Stream.of(fields, cards, keywords)
                .flatMap(i -> i)
                .collect(Collectors.joining("\n"));

        FileUtils.writeStringToFile(getSetPath().toFile(), setString, Charset.defaultCharset());
    }

    private String writeCard(Card card) {
        return "card:\n" + writeFields(1, card.fields);
    }

    private String writeKeyword(Keyword keyword) {
        return "keyword:\n" + writeFields(1, keyword.fields);
    }

    private String writeFields(int depth, Map<String, String> fields) {
        return fields.entrySet().stream()
                .map(e -> writeField(depth, e.getKey(), e.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private String writeField(int depth, String key, Object value) {
        String keyDepth = "\t".repeat(Math.max(0, depth));
        if (value instanceof Node node) {
            return "%s%s:\n%s".formatted(keyDepth, key,
                    writeFields(depth + 1, node.fields));
        } else if (value instanceof String && ((String) value).contains("\n")) {
            //BigString field takes up multiple lines.
            String valueDepth = "\t".repeat(Math.max(0, depth + 1));
            return "%s%s:\n%s%s".formatted(keyDepth, key, valueDepth, value);
        } else {
            return "%s%s: %s".formatted(keyDepth, key, value);
        }
    }

    /*============================== IMPORT/EXPORT ==============================*/

    private static Path createDirectory(Path basePath) throws IOException {
        File directory = basePath.toFile();
        if (overwriteExisting) {
            FileUtils.deleteDirectory(directory);
            if (!directory.mkdirs()) {
                throw new IllegalStateException("Couldn't create directory: " + directory);
            }
        } else if (directory.exists()) {
            throw new IllegalStateException("Directory already exists: " + directory);
        }
        return basePath;
    }


    /* === Internal Classes === */

    public static class Node<V> {
        public final Map<String, V> fields = new LinkedHashMap<>();
    }

    public static class Set extends Node<Object> {
        public List<Card> cards = new ArrayList<>();
        public List<Keyword> keywords = new ArrayList<>();
    }

    public static class Card extends Node<String> {

    }

    public static class Keyword extends Node<String> {

    }

}

