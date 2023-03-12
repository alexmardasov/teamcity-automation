package com.jetbrains.teamcity.services.utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Deserializer {

    Object o;
    public Deserializer(Object o) {
        this.o = o;
    }
    public <T> T deserialize(String pathToJson, Class<T> clazz) {
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = o.getClass().getClassLoader().getResourceAsStream(pathToJson);
            return objectMapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON file could not be found or read.", e);
        }
    }
}
