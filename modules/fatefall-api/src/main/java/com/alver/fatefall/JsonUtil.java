package com.alver.fatefall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJson(T object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String string) {
        try {
            return (T) OBJECT_MAPPER.readTree(string);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(File file) {
        try {
            return (T) OBJECT_MAPPER.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
