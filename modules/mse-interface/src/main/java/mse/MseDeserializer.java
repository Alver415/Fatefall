package mse;

import mse.data.MseCard;
import mse.data.MseKeyword;
import mse.data.MseNode;
import mse.data.MseSet;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MseDeserializer {

    public MseSet readSet(String string) {
        return readSet(List.of(string.split("\n")));
    }

    public MseSet readSet(List<String> lines) {
        ListIterator<String> iterator = lines.listIterator();
        MseSet set = new MseSet();

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (!line.contains(":")) {
                //This is only true at the top level
                throw new IllegalStateException("Line must contain colon ':' deliminator to split key and value. '%s'".formatted(line));
            }
            int index = line.indexOf(":");
            String key = line.substring(0, index).trim();

            if ("card".equals(key)) {
                MseCard value = readCard(iterator, 0);
                set.cards.add(value);
            } else if ("keyword".equals(key)) {
                MseKeyword value = readKeyword(iterator, 0);
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
        MseNode<Object> node = new MseNode<>();
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

    private MseCard readCard(ListIterator<String> iterator, int parentDepth) {
        MseCard card = new MseCard();
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

    private MseKeyword readKeyword(ListIterator<String> iterator, int parentDepth) {
        MseKeyword keyword = new MseKeyword();
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
}
