package mse.set;

import mse.ZipUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetManager {

    private static final Path JMSE_SET_DIRECTORY = Path.of("jmse_sets");

    public static void main(String... args) throws IOException {
        Path sourceSet = Path.of("mse_sets/champions.mse-set");

        SetManager setManager = new SetManager();
        String setName = "TestImport";
        Path destinationDirectory = JMSE_SET_DIRECTORY.resolve(setName);
        setManager.importSet(sourceSet, destinationDirectory);

        Path setPath = setManager.findSetFile(destinationDirectory);
        if (setPath == null) {
            throw new IOException("Failed to find set file under: " + destinationDirectory);
        }

        Set set = setManager.readSet(setPath);
        System.out.println(setManager.writeSet(set));
    }

    /*============================== IMPORT ==============================*/

    public void importSet(Path source, Path destination) throws IOException {
        clearDirectory(destination);
        destination = Files.copy(source, destination.resolve(source.getFileName()));
        String baseSourceFileName = source.getFileName().toString().split("\\.")[0];
        ZipUtil.unzip(source, destination.resolveSibling(baseSourceFileName));
    }

    private void clearDirectory(Path directory) throws IOException {
        clearDirectory(directory.toFile());
    }

    private void clearDirectory(File directory) throws IOException {
        //TODO: Implement check before overwriting existing data.
        FileUtils.deleteDirectory(directory);
        if (!directory.mkdirs()) {
            throw new IllegalStateException("Couldn't create directory: " + directory);
        }
    }

    /*============================== LOAD ==============================*/

    private Path findSetFile(Path setDir) {
        if (setDir.getFileName().toString().equals("set")) {
            return setDir;
        }
        File[] files = setDir.toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                Path path = findSetFile(file.toPath());
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

    private Set readSet(Path setFile) throws IOException {
        List<String> lines = Files.readAllLines(setFile);
        ListIterator<String> iterator = lines.listIterator();
        Set set = new Set();

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

    /* === Write === */

    private String writeSet(Set set) {
        Stream<String> fields = set.fields.entrySet().stream()
                .map(e -> writeField(0, e.getKey(), e.getValue()));

        Stream<String> cards = set.cards.stream()
                .map(this::writeCard);

        Stream<String> keywords = set.keywords.stream()
                .map(this::writeKeyword);

        return Stream.of(fields, cards, keywords)
                .flatMap(i -> i)
                .collect(Collectors.joining("\n"));
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


    /* === Internal Classes === */

    public static class Node<V> {
        protected final Map<String, V> fields = new LinkedHashMap<>();
    }

    public static class Set extends Node<Object> {
        protected List<Card> cards = new ArrayList<>();
        protected List<Keyword> keywords = new ArrayList<>();
    }

    public static class Card extends Node<String> {

    }

    public static class Keyword extends Node<String> {

    }

}

