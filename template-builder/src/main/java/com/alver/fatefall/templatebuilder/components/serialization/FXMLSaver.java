package com.alver.fatefall.templatebuilder.components.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.Node;

import java.io.File;
import java.io.IOException;

public class FXMLSaver {

    public static void save(File file, Node node) {
        try {
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(file, node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
